//package model;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//import database.SQLConnection;
//import model.person.AcademicLevel;
//import model.person.BirthPlace;
//import model.person.Education;
//import model.person.Ethnic;
//import model.person.IdentityCard;
//import model.person.InforPerson;
//import model.person.Nationality;
//import model.person.OriginPlace;
//import model.person.Person;
//import model.person.Residence;
//import model.person.ResidenceType;
//import model.person.TemporaryAbsence;
//import model.person.TemporaryResidence;
//import model.person.Work;
//
//public class Object {
//	public static ArrayList<InforPerson> danhSachNhanKhau = new ArrayList<InforPerson>();
//	
//	public void initObject() {
//		SQLConnection.ConnectData();
//		
//		SQLConnection.DisconnectData();
//	}
//	
//	private void setDanhSachNK() {
//		danhSachNhanKhau.clear();
//		String sql = "select p.*, e.Name as EthName, e.OtherName as EOtherName, n.Name as NaName, bp.NationID as BirPlNID, bp.ProvinceID as BirPlPID, bp.DistrictID as BirPlDID, bp.CommuneID as BirPlCID, op.NationID as OriPlNID, op.ProvinceID as OriPlPID, op.DistrictID as OriPlDID, op.CommuneID as OriPlCID, ic.Number as CCCD, ic.RegisterDate as IDDate, ic.RegisterPlace as IDPlace, r.ResidenceTypeID as ReTyID, r.PrePermanentAddress as PrPeAd, r.BookID as BoID, r.RelationshipWithHead as ReWiHe, rt.Name as ReTyName, edu.AcademicLevel as AcLe, edu.PrimarySchool as PrSc, edu.JuniorHighSchool as JuHiSc, edu.HighSchool as HiSc, edu.Class as Class, al.Name as AcLeName, w.Job as Job, w.Place as WoPl, w.ModifiedDate as WoDate, ta.CertificateID as TACeID, ta.TempResidencePlace as TATeRePlace, ta.FromDate as TAFrDate, ta.ToDate as TAToDate, ta.CertifiedDate as TACeDate, tr.CertificateID as TRCeID, tr.FromDate as TRFrDate, tr.ToDate as TRToDate, tr.ProvinceID as TRPID, tr.DistrictID as TRDID, tr.CommuneID as TRCID, tr.DetailAddress as TRDeAd, tr.CertifiedDate as TRCeDate from Person.Person p join Person.BirthPlace bp on bp.PersonID = p.PersonID join Person.OriginPlace op on op.PersonID = p.PersonID join Person.Ethnic e on e.EthnicID = p.EthnicID join Person.Nationality n on n.NationalityID = p.NationalityID join Person.IdentityCard ic on ic.PersonID = p.PersonID join Person.Residence r on r.PersonID = p.PersonID join Person.ResidenceType rt on rt.ResidenceTypeID = r.ResidenceTypeID left join Person.Education edu on edu.PersonID = p.PersonID left join Person.AcademicLevel al on al.Level = edu.AcademicLevel left join Person.Work w on w.PersonID = p.PersonID left join Person.TemporaryAbsent ta on ta.PersonID = p.PersonID left join Person.TemporaryResidence tr on tr.PersonID = p.PersonID ";
//		
//		try {
//			ResultSet result = SQLConnection.statement.executeQuery(sql);
//			
//			while(result.next()) {
//				danhSachNhanKhau.add(new InforPerson(new Person(result.getInt("PersonID"), result.getString("FullName"), result.getString("NickName"), result.getDate("BirthDate"), result.getInt("Gender"), result.getString("EthnicID"), result.getString("NationalityID")), 
//									 new Nationality(result.getString("NationalityID"), result.getString("NaName")),
//									 new Ethnic(result.getString("EthnicID"), result.getString("EthName")), 
//									 new Work(result.getInt("PersonID"), result.getString("Job"), result.getString("WoPl"), result.getDate("WoDate")),
//									 new IdentityCard(result.getInt("PersonID"), result.getString("CCCD"), result.getDate("IDDate"), result.getString("IDPlace")),
//									 new OriginPlace(result.getInt("PersonID"), result.getString("OriPlNID"), result.getString("OriPlPID"), result.getString("OriPlDID"), result.getString("OriPlCID")),
//									 new BirthPlace(result.getInt("PersonID"), result.getString("BirPlNID"), result.getString("BirPlPID"), result.getString("BirPlDID"), result.getString("BirPlCID")),
//									 new TemporaryAbsence(result.getInt("TACeID"), result.getInt("PersonID"), result.getString("TATeRePlace"), result.getDate("TAFrDate"), result.getDate("TAToDate"), result.getDate("TACeDate")),
//									 new TemporaryResidence(result.getInt("TRCeID"), result.getInt("PersonID"), result.getDate("TRFrDate"), result.getDate("TRToDate"), result.getString("TRPID"), result.getString("TRDID"), result.getString("TRCID"), result.getString("TRDeAd"), result.getDate("TRCeDate")), 
//									 new Education(result.getInt("PersonID"), result.getInt("AcLe"), result.getString("PrSc"), result.getString("JuHiSc"), result.getString("HiSc"), result.getString("Class")), 
//									 new AcademicLevel(result.getInt("AcLe"), result.getString("AcLeName")), 
//									 new ResidenceType(result.getInt("ReTyID"), result.getString("ReTyName")),
//									 new Residence(result.getInt("PersonID"), result.getInt("ReTyID"), result.getString("PrPeAd"), result.getInt("BoID"), result.getString("ReWiHe"))));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//	
//	private static String toNation(String nationID) {
//		String a = null;
//		String sql = "select * from Address.Nation where NationID = '" + nationID + "'";
//		
//		try {
//			ResultSet result = SQLConnection.statement.executeQuery(sql);
//			if(result.next()) 
//				a = result.getString("Name");
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return a;
//	}
//	
//	private static String toProvince(String provinceID) {
//		String a = null;
//		String sql = "select Name from Address.Province where provinceID = '" + provinceID + "'";
//		
//		try {
//			ResultSet result = SQLConnection.statement.executeQuery(sql);
//			if(result.next()) 
//				a = result.getString("Name");
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return a;
//	}
//	
//	private static String toDistrict(String districtID) {
//		String a = null;
//		String sql = "select Name from Address.District where districtID = '" + districtID + "'";
//		
//		try {
//			ResultSet result = SQLConnection.statement.executeQuery(sql);
//			if(result.next()) 
//				a = result.getString("Name");
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return a;
//	}
//	
//	private static String toCommune(String communeID) {
//		String a = null;
//		String sql = "select Name from Address.Commune where communeID = '" + communeID + "'";
//		
//		try {
//			ResultSet result = SQLConnection.statement.executeQuery(sql);
//			if(result.next()) 
//				a = result.getString("Name");
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return a;
//	}
//	
//	public static String toPlace(String nationID, String provinceID, String districtID, String communeID) {
//		return toCommune(communeID) + ", " + toDistrict(districtID) + ", " + toProvince(provinceID) + ", " + toNation(nationID);
//	}
//}
