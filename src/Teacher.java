public class Teacher {
    int id;
    String name;
    String faculty;
    String address;
    String email;

    public Teacher(int id, String name, String faculty, String  address, String email) {
        this.id = id;
        this.name = name;
        this.faculty = faculty;
        this.address = address;
        this.email = email;
    }

    public Teacher() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
