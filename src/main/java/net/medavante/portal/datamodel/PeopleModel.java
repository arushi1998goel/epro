package net.medavante.portal.datamodel;

public class PeopleModel {

    // Required parameters
    private String firstName;
    private String lastName;

    // Optional parameter
    private String userid;
    private String nameAttributePrefix;
    private String degree;
    private String comments;

    private PeopleModel(PeopleModelBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.userid = builder.userid;
        this.nameAttributePrefix = builder.nameAttributePrefix;
        this.degree = builder.degree;
        this.comments = builder.comments;

    }

    @Override
    public String toString() {
        return "PeopleModel [firstName=" + firstName + ", lastName=" + lastName + ", userid=" + userid
                + ", nameAttributePrefix=" + nameAttributePrefix + ", degree=" + degree + ", comments=" + comments
                + "]";
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUseid() {
        return userid;
    }

    public String getNameAttributePrefix() {
        return nameAttributePrefix;
    }

    public String getDegree() {
        return degree;
    }

    public String getComments() {
        return comments;
    }

    public static class PeopleModelBuilder {

        // Required parameters
        private String firstName;
        private String lastName;

        // Optional parameter
        private String userid;
        private String nameAttributePrefix;
        private String degree;
        private String comments;

        public PeopleModelBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public PeopleModelBuilder setUserid(String userid) {
            this.userid = userid;
            return this;
        }

        public PeopleModelBuilder setNameAttributePrefix(String nameAttributePrefix) {
            this.nameAttributePrefix = nameAttributePrefix;
            return this;
        }

        public PeopleModelBuilder setDegree(String degree) {
            this.degree = degree;
            return this;
        }

        public PeopleModelBuilder setComments(String comments) {
            this.comments = comments;
            return this;
        }

        public PeopleModel build() {
            return new PeopleModel(this);
        }
    }
}
