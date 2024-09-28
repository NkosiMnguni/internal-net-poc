package internalnetpoc.dto;



public record PersonDTO
    (String empId,
     String fullName,
     String email,
     String officeCode,
     String managerEmployeeNumber){}

//public record PersonDTO
//        (String empId,
//         String fullName,
//         String email,
//         String officeCode,
//         Manager manager,
//         RoleOccupant roleOccupant,
//         List<PersonSkill> skills,
//         List<Contribution> projects,
//         GuildMember guild,
//         List<Interaction> interactions){}