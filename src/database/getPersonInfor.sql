use [PopulationManagement]
go

if exists(select 1 from sys.procedures where name = 'getPersonInfor')
drop procedure getPersonInfor
go

create procedure getPersonInfor
@id int
as
begin
	select
		Person.PersonID,
		Person.FullName,
		Person.Nickname,
		case when Person.Gender = '1' then N'Nam' else N'Nữ' end [Gender],
		convert(varchar(10), Person.BirthDate, 103) [BirthDate],
		Ethnic.Name [Ethnic],
		Nationality.Name [Nationality],
		IdentityCard.Number [CardNumber],
		convert(varchar(10), IdentityCard.RegisterDate, 103) [RegisterDate],
		IdentityCard.RegisterPlace,
		isnull((select concat(c.Name, ', ', d.Name, ', ', p.Name, ', ', n.Name)
				from Person.BirthPlace [b]
					join Address.Nation [n] on b.NationID = n.NationID
					join Address.Province [p] on b.ProvinceID = p.ProvinceID
					join Address.District [d] on b.DistrictID = d.DistrictID
					join Address.Commune [c] on b.CommuneID = c.CommuneID
				where b.PersonID = @id), null) [BirthPlace],
		isnull((select concat(c.Name, ', ', d.Name, ', ', p.Name, ', ', n.Name)
				from Person.OriginPlace [o]
					join Address.Nation [n] on o.NationID = n.NationID
					join Address.Province [p] on o.ProvinceID = p.ProvinceID
					join Address.District [d] on o.DistrictID = d.DistrictID
					join Address.Commune [c] on o.CommuneID = c.CommuneID
				where o.PersonID = @id), null) [OriginPlace],
		isnull((select Job from Person.Work where PersonID = @id), null) [Job],
		isnull((select Place from Person.Work where PersonID = @id), null) [WorkPlace],
		isnull((select URL from Person.Photo where PersonID = @id), null) [PhotoURL]
			
	from Person.Person [Person]
		join Person.IdentityCard [IdentityCard] on IdentityCard.PersonID = Person.PersonID
		join Person.Ethnic [Ethnic] on Person.EthnicID = Ethnic.EthnicID
		join Person.Nationality [Nationality] on Person.NationalityID = Nationality.NationalityID
	
	where Person.PersonID = @id
end
go