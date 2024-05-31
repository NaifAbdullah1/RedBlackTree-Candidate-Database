package rbt;

public class Candidate implements Comparable<Candidate> {

    private int id;
    private String fullName;
    private String nationality;
    private String city;
    private double latitude;
    private double longitude;
    private char gender;
    private int age;
    private double englishGrade;
    private double mathGrade;
    private double sciencesGrade;
    private double languageGrade;
    private int portfolioRating;
    private int coverLetterRating;
    private int referenceLetterRating;

    public Candidate() {

    }

    public Candidate(int id, String fullName, String nationality, String city, double latitude, double longitude,
            char gender, int age, double englishGrade, double mathGrade, double sciencesGrade,
            double languageGrade, int portfolioRating, int coverLetterRating, int referenceLetterRating) {
        this.id = id;
        this.fullName = fullName;
        this.nationality = nationality;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.gender = gender;
        this.age = age;
        this.englishGrade = englishGrade;
        this.mathGrade = mathGrade;
        this.sciencesGrade = sciencesGrade;
        this.languageGrade = languageGrade;
        this.portfolioRating = portfolioRating;
        this.coverLetterRating = coverLetterRating;
        this.referenceLetterRating = referenceLetterRating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getEnglishGrade() {
        return englishGrade;
    }

    public void setEnglishGrade(double englishGrade) {
        this.englishGrade = englishGrade;
    }

    public double getMathGrade() {
        return mathGrade;
    }

    public void setMathGrade(double mathGrade) {
        this.mathGrade = mathGrade;
    }

    public double getSciencesGrade() {
        return sciencesGrade;
    }

    public void setSciencesGrade(double sciencesGrade) {
        this.sciencesGrade = sciencesGrade;
    }

    public double getLanguageGrade() {
        return languageGrade;
    }

    public void setLanguageGrade(double languageGrade) {
        this.languageGrade = languageGrade;
    }

    public int getPortfolioRating() {
        return portfolioRating;
    }

    public void setPortfolioRating(int portfolioRating) {
        this.portfolioRating = portfolioRating;
    }

    public int getCoverLetterRating() {
        return coverLetterRating;
    }

    public void setCoverLetterRating(int coverLetterRating) {
        this.coverLetterRating = coverLetterRating;
    }

    public int getReferenceLetterRating() {
        return referenceLetterRating;
    }

    public void setReferenceLetterRating(int referenceLetterRating) {
        this.referenceLetterRating = referenceLetterRating;
    }

    @Override
    public String toString() {
        return "Candidate{"
                + "id=" + id
                + ", fullName='" + fullName + '\''
                + ", nationality='" + nationality + '\''
                + ", city='" + city + '\''
                + ", latitude=" + latitude
                + ", longitude=" + longitude
                + ", gender=" + gender
                + ", age=" + age
                + ", englishGrade=" + englishGrade
                + ", mathGrade=" + mathGrade
                + ", sciencesGrade=" + sciencesGrade
                + ", languageGrade=" + languageGrade
                + ", portfolioRating=" + portfolioRating
                + ", coverLetterRating=" + coverLetterRating
                + ", referenceLetterRating=" + referenceLetterRating
                + '}';
    }

    @Override
    public int compareTo(Candidate otherCandidate) {
        // Compare all attributes
        int compare = Integer.compare(this.id, otherCandidate.id);
        if (compare != 0) {
            return compare;
        }
        compare = this.fullName.compareTo(otherCandidate.fullName);
        if (compare != 0) {
            return compare;
        }
        compare = this.nationality.compareTo(otherCandidate.nationality);
        if (compare != 0) {
            return compare;
        }
        compare = this.city.compareTo(otherCandidate.city);
        if (compare != 0) {
            return compare;
        }
        compare = Double.compare(this.latitude, otherCandidate.latitude);
        if (compare != 0) {
            return compare;
        }
        compare = Double.compare(this.longitude, otherCandidate.longitude);
        if (compare != 0) {
            return compare;
        }
        compare = Character.compare(this.gender, otherCandidate.gender);
        if (compare != 0) {
            return compare;
        }
        compare = Integer.compare(this.age, otherCandidate.age);
        if (compare != 0) {
            return compare;
        }
        compare = Double.compare(this.englishGrade, otherCandidate.englishGrade);
        if (compare != 0) {
            return compare;
        }
        compare = Double.compare(this.mathGrade, otherCandidate.mathGrade);
        if (compare != 0) {
            return compare;
        }
        compare = Double.compare(this.sciencesGrade, otherCandidate.sciencesGrade);
        if (compare != 0) {
            return compare;
        }
        compare = Double.compare(this.languageGrade, otherCandidate.languageGrade);
        if (compare != 0) {
            return compare;
        }
        compare = Integer.compare(this.portfolioRating, otherCandidate.portfolioRating);
        if (compare != 0) {
            return compare;
        }
        compare = Integer.compare(this.coverLetterRating, otherCandidate.coverLetterRating);
        if (compare != 0) {
            return compare;
        }
        return Integer.compare(this.referenceLetterRating, otherCandidate.referenceLetterRating);
    }

}
