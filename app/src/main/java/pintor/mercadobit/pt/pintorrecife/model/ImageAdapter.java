package pintor.mercadobit.pt.pintorrecife.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import pintor.mercadobit.pt.pintorrecife.R;

public class ImageAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Galeria>list;


    public ImageAdapter(Context context, ArrayList<Galeria> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        Galeria galeria = list.get(position);
        View layout;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = inflater.inflate(R.layout.custom_layout, null);
        }
        else{
            layout = convertView;
        }

        ImageView imageView = (ImageView)layout.findViewById(R.id.id_img);
        imageView.setImageResource(galeria.getImg(position));


        return layout;
    }
}
