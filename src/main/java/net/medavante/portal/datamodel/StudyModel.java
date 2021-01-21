package net.medavante.portal.datamodel;

public class StudyModel {

    // Required parameters
    private String studyName;
    private String abbreviation;
    private String phase;
    private String sponsor;

    // Optional parameter
    private String drugName;

    private StudyModel(StudyModelBuilder builder) {
        this.studyName = builder.studyName;
        this.abbreviation = builder.abbreviation;
        this.phase = builder.phase;
        this.sponsor = builder.sponsor;
        this.drugName = builder.drugName;

    }

    @Override
    public String toString() {
        return "StudyModel [studyName=" + studyName + ", abbreviation=" + abbreviation + ", phase=" + phase
                + ", sponsor=" + sponsor + ", drugName=" + drugName + "]";
    }

    public String getStudyName() {
        return studyName;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getPhase() {
        return phase;
    }

    public String getSponsor() {
        return sponsor;
    }

    public String getDrugName() {
        return drugName;
    }

    public static class StudyModelBuilder {

        // Required parameters
        private String studyName;
        private String abbreviation;
        private String phase;
        private String sponsor;

        // Optional parameter

        private String drugName;

        public StudyModelBuilder(String studyName, String abbreviation, String phase, String sponsor) {
            this.studyName = studyName;
            this.abbreviation = abbreviation;
            this.phase = phase;
            this.sponsor = sponsor;
        }

        public StudyModelBuilder setDrugName(String drugName) {
            this.drugName = drugName;
            return this;
        }

        public StudyModel build() {
            return new StudyModel(this);
        }
    }
}
