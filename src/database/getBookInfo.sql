use [PopulationManagement]
go

if exists(select 1 from sys.procedures where name = 'getBookInfo')
drop procedure getBookInfo
go

create procedure getBookInfo
@id int
as
begin
	select 
		Book.BookID,
		isnull((select concat(
					isnull((select DetailAddress + ', ' from Household.Book where BookID = @id), ''),
					isnull((select Name + ', ' from Address.Street where StreetID = (select StreetID from Household.Book where BookID = @id)), ''),
					c.Name, ', ', 
					d.Name, ', ', 
					p.Name)
				from Household.Book [b]
					join Address.Province [p] on b.ProvinceID = p.ProvinceID
					join Address.District [d] on b.DistrictID = d.DistrictID
					join Address.Commune [c] on b.CommuneID = c.CommuneID
				where b.BookID = @id), null) [ResidencePlace],
		Person.PersonID,
		Person.FullName,
		case when Person.Gender = '1' then N'Nam' else N'Nữ' end [Gender],
		convert(varchar(10), Person.BirthDate, 103) [BirthDate],
		Residence.RelationshipWithHead,
		Residence.PrePermanentAddress

	from Person.Residence [Residence]
		join Person.Person [Person] on Residence.PersonID = Person.PersonID
		join Person.ResidenceType [ResidenceType] on Residence.ResidenceTypeID = ResidenceType.ResidenceTypeID
		join Household.Book [Book] on Residence.BookID = Book.BookID
		
	where Book.BookID = @id

	order by (case when RelationshipWithHead = N'Chủ hộ' THEN 0 ELSE 1 END), PersonID
end
go