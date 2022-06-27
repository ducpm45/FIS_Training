package core.model;

public class CaseDetective {
    private String case_number;
    private String detective_badge_number;

    public CaseDetective() {
    }

    public CaseDetective(String case_number, String detective_badge_number) {
        this.case_number = case_number;
        this.detective_badge_number = detective_badge_number;
    }

    public String getCase_number() {
        return case_number;
    }

    public void setCase_number(String case_number) {
        this.case_number = case_number;
    }

    public String getDetective_badge_number() {
        return detective_badge_number;
    }

    public void setDetective_badge_number(String detective_badge_number) {
        this.detective_badge_number = detective_badge_number;
    }
}
