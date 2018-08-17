package pintor.mercadobit.pt.pintorrecife.tasks;

import android.view.MenuItem;

public interface Padrao {

   /* interface Views{

    }
    interface Presenter{

    }
    interface Model{

    } */

    public void onBackPressed();
    public void initViews();
    public boolean onOptionsItemSelected(MenuItem item);
}
