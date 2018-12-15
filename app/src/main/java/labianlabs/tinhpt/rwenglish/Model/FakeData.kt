package labianlabs.tinhpt.rwenglish.Model

import labianlabs.tinhpt.rwenglish.R

class FakeData() {
    fun createDatas(): List<Vocabulary> {
        var v1 = Vocabulary("Tree", "Cây", 1, R.drawable.ic_app_launcher)
        var v1c = Vocabulary("Tree", "Cây", 1, R.mipmap.ic_launcher)
        var v2 = Vocabulary("Laptop", "Máy tính", 2, R.mipmap.ic_launcher)
        var v2c = Vocabulary("Laptop", "Máy tính", 2, R.mipmap.ic_launcher)
        var v3 = Vocabulary("Eat", "Ăn", 3, R.mipmap.ic_launcher)
        var v3c = Vocabulary("Eat", "Ăn", 3, R.mipmap.ic_launcher)
        var v4 = Vocabulary("Mobile", "Di động", 4, R.mipmap.ic_launcher)
        var v4c = Vocabulary("Mobile", "Di động", 4, R.mipmap.ic_launcher)
        var v5 = Vocabulary("Moon", "Mặt Trăng", 5, R.mipmap.ic_launcher)
        var v5c = Vocabulary("Moon", "Mặt Trăng", 5, R.mipmap.ic_launcher)
        var v6 = Vocabulary("Lake", "Hồ", 6, R.mipmap.ic_launcher)
        var v6c = Vocabulary("Lake", "Hồ", 6, R.mipmap.ic_launcher)
        var v7 = Vocabulary("Pen", "Cây viết", 7, R.mipmap.ic_launcher)
        var v7c = Vocabulary("Pen", "Cây viết", 7, R.mipmap.ic_launcher)
        var v8 = Vocabulary("Rule", "Cây thước", 8, R.mipmap.ic_launcher)
        var v8c = Vocabulary("Rule", "Cây thước", 8, R.mipmap.ic_launcher)
        var v9 = Vocabulary("Sound", "Loa", 9, R.mipmap.ic_launcher)
        var v9c = Vocabulary("Sound", "Loa", 9, R.mipmap.ic_launcher)
        var v10 = Vocabulary("See", "Nhìn", 10, R.mipmap.ic_launcher)
        var v10c = Vocabulary("See", "Nhìn", 10, R.mipmap.ic_launcher)
        var v11 = Vocabulary("Heart", "Tim", 11, R.mipmap.ic_launcher)
        var v11c = Vocabulary("Heart", "Tim", 11, R.mipmap.ic_launcher)
        var v12 = Vocabulary("Stomach", "Dạ dày", 12, R.mipmap.ic_launcher)
        var v12c = Vocabulary("Stomach", "Dạ dày", 12, R.mipmap.ic_launcher)
        //set display
        v1.isDisplayImage = true; v1c.isDisplayImage = false
        v2.isDisplayImage = true; v2c.isDisplayImage = false
        v3.isDisplayImage = true; v3c.isDisplayImage = false
        v4.isDisplayImage = true; v4c.isDisplayImage = false
        v5.isDisplayImage = true; v5c.isDisplayImage = false
        v6.isDisplayImage = true; v6c.isDisplayImage = false
        v7.isDisplayImage = true; v7c.isDisplayImage = false
        v8.isDisplayImage = true; v8c.isDisplayImage = false
        v9.isDisplayImage = true; v9c.isDisplayImage = false
        v10.isDisplayImage = true; v10c.isDisplayImage = false
        v11.isDisplayImage = true; v11c.isDisplayImage = false
        v12.isDisplayImage = true; v12c.isDisplayImage = false

//        return arrayListOf(v1, v1c, v2, v2c, v3, v3c, v4, v4c, v5, v5c, v6, v6c, v7, v7c, v8, v8c, v9, v9c, v10, v10c, v11, v11c, v12, v12c)
        return arrayListOf(v1, v1c, v2, v2c, v3, v3c, v4, v4c, v5, v5c, v6, v6c, v7, v7c, v8, v8c, v9, v9c, v10, v10c, v11, v11c,v1c,v1,v2c,v2,v4,v4c)
    }
}