package internalnetpoc.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.neo4j.driver.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internalnetpoc.dao.InitDao;
import internalnetpoc.dto.PersonDTO;
import internalnetpoc.dto.RoleDTO;


@Service
public class InitService {
    private final InitDao initDao;
    @Autowired
    public InitService(InitDao initDao) {
        this.initDao = initDao;
    }
    public void PersonCsvToDb() {
        String filename = "/Users/nkosi/Documents/source/projects/internal-net-poc/src/main/resources/mockdata/employee.csv";
        String line;
        try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String empId = data[0].replace("\"", "");
                String fullName = data[1].replace("\"", "");
                String email = data[11].replace("\"", "");
                String officeCode = data[7].replace("\"", "");
                String managerId = data[8].replace("\"", "");
                String role = data[9].replace("\"", "");
                PersonDTO personPTO = new PersonDTO(empId, fullName, email, officeCode, managerId);
                RoleDTO rolePTO = new RoleDTO(role);
                List<String> skills = new ArrayList<>();
                String guild ="";
                switch(role) {
                    case String s when s.contains("Software Engineer"):
                        skills.addAll(List.of("Java", "Python", "JavaScript", "OOP", "Git", "CI/CD", "SQL", "NoSQL", "Debugging", "Data Structures"));
                        guild = "BOTFE";
                        break;
                    case String s when s.contains("Full Stack Engineer"):
                        skills.addAll(List.of("Java", "Python", "JavaScript", "OOP", "Git", "CI/CD", "SQL", "NoSQL", "Debugging", "Data Structures", "React", "Vue.js"));
                        guild = "BOTFE";
                        break;
                    case String s when s.contains("Front End") || s.contains("Frontend"):
                        skills.addAll(List.of("JavaScript", "React", "Vue.js", "HTML", "CSS", "Debugging", "Data Structures"));
                        guild = "FOTFE";
                        break;
                    case String s when s.contains("Data"):
                        skills.addAll(List.of("Hadoop", "ETL", "SQL", "NoSQL", "Data Analysis", "Machine Learning", "Python", "Debugging", "Data Structures","R","Data Analysis"));
                        guild = "DATA";
                        break;
                    case String s when s.contains("DevOps Engineer") || s.contains("Cloud"):
                        skills.addAll(List.of("GCP", "Cloud Architecture", "Docker", "Kubernetes", "Terraform", "CI/CD", "Jenkins", "GitLab CI"));
                        guild = "GCP";
                        break;
                    case String s when s.contains("QA") || s.contains("Quality"):
                        skills.addAll(List.of("Manual & Automated Testing", "Selenium", "Postman", "Test Case Design", "Regression Testing"));
                        guild = "QA";
                        break;
                    case String s when s.contains("Director") || s.contains("Manager") || s.contains("Lead") || s.contains("Head")  || s.contains("Senior"):
                        skills.addAll(List.of("Leadership", "Strategic Planning", "Budgeting", "Project Management", "Advanced Software Development Skills"));
                        break;
                    case String s when s.contains("Build"):
                        skills.addAll(List.of("CI/CD", "Jenkins", "Docker", "Kubernetes", "Terraform"));
                        guild = "CICD";
                        break;
                    case String s when s.contains("BI") || s.contains("Business"):
                        skills.addAll(List.of("Business Process Modeling", "Process Optimization", "Requirements Gathering", "Business Intelligence", "SQL", "Data Analysis", "ETL"));
                        guild = "BIQUERY";
                        break;
                    default:
                        skills.addAll(List.of("Some Skill0", "Some Skill1", "Some Skill2"));
                        guild = "GUILDX";
                }
                List<String> projects  = List.of(
                    "Affiliate Marketing Platform",
                    "Customer Relationship Management (CRM) System",
                    "Mobile App Development",
                    "Analytics and Reporting Dashboard",
                    "Cloud Infrastructure Optimization",
                    "Payment Gateway Integration",
                    "API Development and Management",
                    "Performance Monitoring and Optimization",
                    "Marketing Automation Tools",
                    "Web Application Security Enhancements",
                    "User Experience (UX) Redesign",
                    "Business Intelligence Solutions",
                    "Data Migration to Cloud",
                    "Scalable Microservices Architecture",
                    "DevOps Automation and CI/CD Pipelines",
                    "New Feature Development for Existing Products",
                    "API Documentation and Developer Portal",
                    "Customer Support Platform Improvements",
                    "A/B Testing and Optimization",
                    "Product Roadmap and Strategy Planning",
                    "Technical Debt Reduction",
                    "Open Source Contribution and Community Engagement",
                    "Data Privacy and Compliance Initiatives",
                    "Cloud-native Application Development",
                    "Enhanced Data Analytics Solutions",
                    "Internal Tools Development",
                    "Freshdesk Integration",
                    "Vue3 Migration"
                );

                List<String> employeeProjects = new ArrayList<>();

                //Projects
                for (int i = 0; i < 3; i++) {
                    int randomIndex = (int) (Math.random() * projects.size());
                    employeeProjects.add(projects.get(randomIndex));
                }
            initDao.savePerson(personPTO, rolePTO, skills,employeeProjects, guild);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void createManagerRelationship(){
        initDao.createManagerRelationship();
    }
    public void createInteractions() {
        initDao.createInteractions();
    }

    public void loadVectorStore() {
        initDao.loadVectorStore();
    }

    public void executeCypherQuery(String cypherQuery) throws ClientException {
        initDao.executeCypher(cypherQuery);
    }

    public void addRepositories() {
        initDao.addRepositories();
    }
}