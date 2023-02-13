package utilities;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonParseException;

import java.io.File;
import java.io.IOException;

//Read/write Json file
public class JsonHelper {

    public static void main(String[] args) {
        JsonHelper tester = new JsonHelper();
        try{
            Students student = new Students();

            student.setAge(10);
            student.setName("Test");
            tester.writeJSON(student);

            Students student1 = tester.readJSON();
            System.out.println(student1);
        }catch (JsonParseException e){
            e.printStackTrace();
        }catch (JsonMappingException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void writeJSON(Students student) throws JsonGenerationException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("jacksonData/student.json"), student);
    }

    private Students readJSON() throws JsonGenerationException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Students student = mapper.readValue(new File("jacksonData/student.json"), Students.class);
        return student;
    }


    static class Students{
        private String name;
        private int age;

        public Students(){

        }

        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name = name;
        }

        public int getAge(){
            return age;
        }

        public void setAge(int age){
            this.age = age;
        }

        public String toString(){
            return "Student [ name: " + name + ", age: " + age + " ]";
        }
    }

}
