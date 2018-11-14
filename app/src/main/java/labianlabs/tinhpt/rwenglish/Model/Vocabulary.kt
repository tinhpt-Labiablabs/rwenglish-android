package labianlabs.tinhpt.rwenglish.Model

data class Vocabulary(val originWord: String, val localizedWord: String, val idWord: Int, val imageResourceStandForWord: Int) {
    var isDisplayImage: Boolean = false
    var isCorrect = false

}
