package labianlabs.tinhpt.rwenglish.Model;

public class Vocabulary {

    private String originWord;
    private String localizedWord;
    private int idWord;
    private int imageResourceStandForWord;
    private boolean isDisplayImage;
    private boolean isCorrect = false;

    public Vocabulary(String originWord, String localizedWord, int idWord, int imageResourceStandForWord) {
        this.originWord = originWord;
        this.idWord = idWord;
        this.localizedWord = localizedWord;
        this.imageResourceStandForWord = imageResourceStandForWord;
    }

    public String getOriginWord() {
        return originWord;
    }

    public String getLocalizedWord() {
        return localizedWord;
    }

    public int getIdWord() {
        return idWord;
    }

    public int getImageResourceStandForWord() {
        return imageResourceStandForWord;
    }

    public boolean isDisplayImage() {
        return isDisplayImage;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setDisplayImage(boolean displayImage) {
        isDisplayImage = displayImage;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

}
