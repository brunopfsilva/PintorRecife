package pintor.mercadobit.pt.pintorrecife.model;

import pintor.mercadobit.pt.pintorrecife.R;

public class Galeria  {

    private String galeria;


    public int getImg(int position){

        switch(position){
            case 0:
                return(R.drawable.pic_1);
            case 1:
                return(R.drawable.pic_2);
            case 2:
                return(R.drawable.pic_3);
            case 3:
                return(R.drawable.pic_3);
            case 4:
                return(R.drawable.pic_4);
            case 5:
                return(R.drawable.pic_5);
            case 6:
                return(R.drawable.pic_6);
            case 7:
                return(R.drawable.pic_7);
            case 8:
                return(R.drawable.pic_8);
            case 9:
                return(R.drawable.pic_9);
            case 10:
                return(R.drawable.pic_10);
            case 11:
                return(R.drawable.pic_11);
            case 12:
                return(R.drawable.pic_12);
            case 13:
                return(R.drawable.pic_13);
            case 14:
                return(R.drawable.pic_14);
            case 15:
                return(R.drawable.pic_15);
            case 16:
                return(R.drawable.pic_16);
            case 17:
                return(R.drawable.pic_17);
            case 18:
                return(R.drawable.pic_18);
            case 19:
                return(R.drawable.pic_19);
            case 20:
                return(R.drawable.pic_20);
            default:
                return(R.drawable.pic_4);
        }
    }

    public String getGaleria() {
        return galeria;
    }

    public void setGaleria(String galeria) {
        this.galeria = galeria;
    }
}
