package Model;

public class sectionMetier {

    private String sectionName;
    private char firstLetter;
    private boolean isActive;
    private int idSection;

    public sectionMetier(String name, boolean isActive, int idSection) {
        this.sectionName = name;
        this.isActive = isActive;
        this.idSection = idSection;
        createFirstLetter();
    }

    private void createFirstLetter(){
        firstLetter = sectionName.charAt(0);
        firstLetter =  Character.toUpperCase(firstLetter);
    }

    public int getIdSection() {
        return idSection;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public char getFirstLetter() {
        return firstLetter;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
