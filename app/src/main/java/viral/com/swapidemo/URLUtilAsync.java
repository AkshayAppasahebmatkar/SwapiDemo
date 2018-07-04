package viral.com.swapidemo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import javax.net.ssl.HttpsURLConnection;

public class URLUtilAsync extends AsyncTask {

	private final String TAG = this.getClass().getName();

	private String url,data;
	private Context context;

	public URLUtilAsync(Context context,String url) {
		this.context = context;
		this.url = url;
		this.execute();
	}
	


	@Override
	protected String doInBackground(Object... params) {

		try {
				data = getDetails();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	private final String getDetails()
			throws IOException {
		String responseString=null;

			HttpURLConnection con=null;
			try {
				/*con = (HttpURLConnection) new URL(url)
						.openConnection();*/
				con = (HttpsURLConnection) new URL(url)
						.openConnection();
				con.setRequestMethod("GET");
				//con.setConnectTimeout(15000);
				//con.setReadTimeout(48000);
				//con.setDoInput(true);
				//con.setDoOutput(true);
				con.connect();
				String strValues="";
//



				if(con.getResponseCode() == 200){
					BufferedReader in = new BufferedReader(
					        new InputStreamReader(con.getInputStream()));
					String inputLine;
					StringBuffer s = new StringBuffer();

					while ((inputLine = in.readLine()) != null) {
						s.append(inputLine);
					}
					in.close();
					responseString = s.toString();
					Log.v(TAG, "Response recvd [" + s.toString() + "]");

				}else{
					con.disconnect();
					Handler handler =  new Handler(context.getMainLooper());
					handler.post( new Runnable(){

						@Override
						public void run() {
							// TODO Auto-generated method stub
//							showAlert("Try Again",context,true);
showAlert();
						}
					});

				}
				

			} catch (Exception e) {
				e.printStackTrace();
			}
			
//			
			
		return responseString;

	}


	@Override
	protected void onPostExecute(Object result) {

		super.onPostExecute(result);
	}
private void showAlert(){
	AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

	// Setting Dialog Title
	alertDialog.setTitle("Connection Issue...");

	// Setting Dialog Message
	alertDialog.setMessage("Try Again");
	alertDialog.setIcon(android.R.drawable.stat_notify_error);
	alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog,int which) {
			try {
				getDetails();
			} catch (IOException e) {

			}

		}
	});

	// Setting Negative "NO" Button
	alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			// Write your code here to invoke NO event

			dialog.cancel();
		}
	});

	// Showing Alert Message
	alertDialog.show();
}

}