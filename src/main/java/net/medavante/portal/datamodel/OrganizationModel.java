package net.medavante.portal.datamodel;

public class OrganizationModel {

    // Required parameters
    private String orgName;
    private String orgAbbreviation;
    private String orgType;

    // Optional parameter
    private String orgSubType;
    private String orgActivated;
    private String orgDeactivated;
    private String orgDeactivateReason;
    private String orgWebSite;
    private String orgComments;

    private OrganizationModel(AddOrganizationModelBuilder builder) {
        this.orgName = builder.orgName;
        this.orgAbbreviation = builder.orgAbbreviation;
        this.orgType = builder.orgType;
        this.orgSubType = builder.orgSubType;
        this.orgActivated = builder.orgActivated;
        this.orgDeactivated = builder.orgDeactivated;
        this.orgDeactivateReason = builder.orgDeactivateReason;
        this.orgWebSite = builder.orgWebSite;
        this.orgComments = builder.orgComments;
    }

    public String getOrgName() {
        return orgName;
    }

    public String getOrgAbbreviation() {
        return orgAbbreviation;
    }

    public String getOrgType() {
        return orgType;
    }

    public String getOrgSubType() {
        return orgSubType;
    }

    public String getOrgActivated() {
        return orgActivated;
    }

    public String getorgDeactivated() {
        return orgDeactivated;
    }

    public String getorgDeactivateReason() {
        return orgDeactivateReason;
    }

    public String getorgWebSite() {
        return orgWebSite;
    }

    public String getorgComments() {
        return orgComments;
    }

    @Override
    public String toString() {
        return "OrganizationModel [orgName=" + orgName + ", orgAbbreviation=" + orgAbbreviation + ", orgType=" + orgType
                + ", orgSubType=" + orgSubType + ", orgActivated=" + orgActivated + ", orgDeactivated=" + orgDeactivated
                + ", orgDeactivateReason=" + orgDeactivateReason + ", orgWebSite=" + orgWebSite + ", orgComments="
                + orgComments + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((orgAbbreviation == null) ? 0 : orgAbbreviation.hashCode());
        result = prime * result + ((orgActivated == null) ? 0 : orgActivated.hashCode());
        result = prime * result + ((orgComments == null) ? 0 : orgComments.hashCode());
        result = prime * result + ((orgDeactivateReason == null) ? 0 : orgDeactivateReason.hashCode());
        result = prime * result + ((orgDeactivated == null) ? 0 : orgDeactivated.hashCode());
        result = prime * result + ((orgName == null) ? 0 : orgName.hashCode());
        result = prime * result + ((orgSubType == null) ? 0 : orgSubType.hashCode());
        result = prime * result + ((orgType == null) ? 0 : orgType.hashCode());
        result = prime * result + ((orgWebSite == null) ? 0 : orgWebSite.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OrganizationModel other = (OrganizationModel) obj;
        if (orgAbbreviation == null) {
            if (other.orgAbbreviation != null)
                return false;
        } else if (!orgAbbreviation.equals(other.orgAbbreviation))
            return false;
        if (orgActivated == null) {
            if (other.orgActivated != null)
                return false;
        } else if (!orgActivated.equals(other.orgActivated))
            return false;
        if (orgComments == null) {
            if (other.orgComments != null)
                return false;
        } else if (!orgComments.equals(other.orgComments))
            return false;
        if (orgDeactivateReason == null) {
            if (other.orgDeactivateReason != null)
                return false;
        } else if (!orgDeactivateReason.equals(other.orgDeactivateReason))
            return false;
        if (orgDeactivated == null) {
            if (other.orgDeactivated != null)
                return false;
        } else if (!orgDeactivated.equals(other.orgDeactivated))
            return false;
        if (orgName == null) {
            if (other.orgName != null)
                return false;
        } else if (!orgName.equals(other.orgName))
            return false;
        if (orgSubType == null) {
            if (other.orgSubType != null)
                return false;
        } else if (!orgSubType.equals(other.orgSubType))
            return false;
        if (orgType == null) {
            if (other.orgType != null)
                return false;
        } else if (!orgType.equals(other.orgType))
            return false;
        if (orgWebSite == null) {
            if (other.orgWebSite != null)
                return false;
        } else if (!orgWebSite.equals(other.orgWebSite))
            return false;
        return true;
    }

    public static class AddOrganizationModelBuilder {

        // Required parameters
        private String orgName;
        private String orgAbbreviation;
        private String orgType;

        // Optional parameter
        private String orgSubType;
        private String orgActivated;
        private String orgDeactivated;
        private String orgDeactivateReason;
        private String orgWebSite;
        private String orgComments;

        public AddOrganizationModelBuilder(String orgName, String orgAbbreviation, String orgType) {
            this.orgName = orgName;
            this.orgAbbreviation = orgAbbreviation;
            this.orgType = orgType;
        }

        public AddOrganizationModelBuilder setOrgSubType(String orgSubType) {
            this.orgSubType = orgSubType;
            return this;
        }

        public AddOrganizationModelBuilder setorgActivated(String orgActivated) {
            this.orgActivated = orgActivated;
            return this;
        }

        public AddOrganizationModelBuilder setorgDeactivated(String orgDeactivated) {
            this.orgDeactivated = orgDeactivated;
            return this;
        }

        public AddOrganizationModelBuilder setorgDeactivateReason(String orgDeactivateReason) {
            this.orgDeactivateReason = orgDeactivateReason;
            return this;
        }

        public AddOrganizationModelBuilder setorgWebSite(String orgWebSite) {
            this.orgWebSite = orgWebSite;
            return this;
        }

        public AddOrganizationModelBuilder setorgComments(String orgComments) {
            this.orgComments = orgComments;
            return this;
        }

        public OrganizationModel build() {
            return new OrganizationModel(this);
        }
    }
}
