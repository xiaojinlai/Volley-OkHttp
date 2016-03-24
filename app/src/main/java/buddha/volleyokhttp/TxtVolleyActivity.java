package buddha.volleyokhttp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import buddha.volleyokhttp.http.OkHttpStack;

/**
 * @ClassName: TxtVolleyActivity
 * @author Xiao JinLai
 * @Date 2015-7-29 下午5:47:06
 * @Description：文本类型的Volley
 */
public class TxtVolleyActivity extends Activity implements OnClickListener {

	protected static final String TAG = "@@@TxtVolleyActivity";

	private TextView mTvVolley;
	private RequestQueue mQueue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initControl();
	}

	/**
	 * 加载控件
	 */
	private void initControl() {

		setContentView(R.layout.activity_txt_volley);
		findViewById(R.id.btnVolley).setOnClickListener(this);
		findViewById(R.id.btnVolleyJson).setOnClickListener(this);

		mQueue = Volley.newRequestQueue(this,new OkHttpStack());
		mTvVolley = (TextView) this.findViewById(R.id.tvVolley);
	}

	private void initVolley() {

		StringRequest tStringRequest = new StringRequest(
				"http://www.baidu.com", new Response.Listener<String>() {

					@Override
					public void onResponse(String response) {

						mTvVolley.setText("");
						mTvVolley.setText(response);
					}

				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {

					}
				});

		mQueue.add(tStringRequest);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btnVolley:

			initVolley();
			break;
		case R.id.btnVolleyJson:

			initVolleyJson();
			break;
		}
	}

	public void initVolleyJson() {

		JsonObjectRequest tRequest = new JsonObjectRequest(
				"http://www.weather.com.cn/adat/sk/101010100.html", null,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {

						mTvVolley.setText("");
						mTvVolley.setText(response.toString());
						Log.e(TAG, response.toString());
					}

				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {

						Log.e(TAG, "出错啦！"+error.getMessage(), error);
					}
				});

		mQueue.add(tRequest);
	}
	
}
