package com.rilintech.fragment_301_huxike_android.adapter;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.rilintech.fragment_301_huxike_android.R;
import com.rilintech.fragment_301_huxike_android.activity.AlertDialog;
import com.rilintech.fragment_301_huxike_android.activity.QuestionnaireActivity;
import com.rilintech.fragment_301_huxike_android.bean.VoteSubmitItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoteSubmitAdapter extends PagerAdapter {

	private QuestionnaireActivity mContext;
	// 传递过来的页面view的集合
	private List<View> viewItems;
	// 每个item的页面view
	private View convertView;
	// 传递过来的所有数据
	private ArrayList<VoteSubmitItem> dataItems;
	// 题目选项的adapter
	private VoteSubmitListAdapter listAdapter;
	//点击listview的item，score的集合
	private ArrayList<Integer> scoresItems;
	private Map<Integer, Integer> scoreMaps=new HashMap<Integer, Integer>();
	private int[]scoreArr;
	//判断有没选择某个页面的选项
	private Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();

	public VoteSubmitAdapter(QuestionnaireActivity context, List<View> viewItems, ArrayList<VoteSubmitItem> dataItems, ArrayList<Integer> scoresItems) {
		mContext = context;
		this.viewItems = viewItems;
		this.dataItems = dataItems;
		this.scoresItems=scoresItems;
		scoreArr=new int[viewItems.size()];
	}
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(viewItems.get(position));
	}

	@Override
	public Object instantiateItem(ViewGroup container, final int position) {
		final ViewHolder holder = new ViewHolder();
		convertView = viewItems.get(position);
		holder.title = (TextView) convertView.findViewById(R.id.vote_submit_title);
		holder.question = (TextView) convertView.findViewById(R.id.vote_submit_question);
		//holder.listView = (ListView) convertView.findViewById(R.id.vote_submit_listview);
		holder.previousBtn = (LinearLayout) convertView.findViewById(R.id.vote_submit_linear_previous);
		holder.nextBtn = (LinearLayout) convertView.findViewById(R.id.vote_submit_linear_next);
		holder.nextText = (TextView) convertView.findViewById(R.id.vote_submit_next_text);
		holder.nextImage = (ImageView) convertView.findViewById(R.id.vote_submit_next_image);

		//------------------------------------------------
		holder.layoutA=(LinearLayout) convertView.findViewById(R.id.activity_prepare_test_layout_a);
		holder.layoutB=(LinearLayout) convertView.findViewById(R.id.activity_prepare_test_layout_b);
		holder.layoutC=(LinearLayout) convertView.findViewById(R.id.activity_prepare_test_layout_c);
		holder.layoutD=(LinearLayout) convertView.findViewById(R.id.activity_prepare_test_layout_d);
		holder.layoutE=(LinearLayout) convertView.findViewById(R.id.activity_prepare_test_layout_e);
		holder.layoutF=(LinearLayout) convertView.findViewById(R.id.activity_prepare_test_layout_f);
		holder.layoutG=(LinearLayout) convertView.findViewById(R.id.activity_prepare_test_layout_g);

		holder.ivA=(ImageView) convertView.findViewById(R.id.vote_submit_select_image_a);
		holder.ivB=(ImageView) convertView.findViewById(R.id.vote_submit_select_image_b);
		holder.ivC=(ImageView) convertView.findViewById(R.id.vote_submit_select_image_c);
		holder.ivD=(ImageView) convertView.findViewById(R.id.vote_submit_select_image_d);
		holder.ivE=(ImageView) convertView.findViewById(R.id.vote_submit_select_image_e);
		holder.ivF=(ImageView) convertView.findViewById(R.id.vote_submit_select_image_f);
		holder.ivG=(ImageView) convertView.findViewById(R.id.vote_submit_select_image_g);

		holder.ivA_=(ImageView) convertView.findViewById(R.id.vote_submit_select_image_a_);
		holder.ivB_=(ImageView) convertView.findViewById(R.id.vote_submit_select_image_b_);
		holder.ivC_=(ImageView) convertView.findViewById(R.id.vote_submit_select_image_c_);
		holder.ivD_=(ImageView) convertView.findViewById(R.id.vote_submit_select_image_d_);
		holder.ivE_=(ImageView) convertView.findViewById(R.id.vote_submit_select_image_e_);
		holder.ivF_=(ImageView) convertView.findViewById(R.id.vote_submit_select_image_f_);
		holder.ivG_=(ImageView) convertView.findViewById(R.id.vote_submit_select_image_g_);

		holder.tvA=(TextView) convertView.findViewById(R.id.vote_submit_select_text_a);
		holder.tvB=(TextView) convertView.findViewById(R.id.vote_submit_select_text_b);
		holder.tvC=(TextView) convertView.findViewById(R.id.vote_submit_select_text_c);
		holder.tvD=(TextView) convertView.findViewById(R.id.vote_submit_select_text_d);
		holder.tvE=(TextView) convertView.findViewById(R.id.vote_submit_select_text_e);
		holder.tvF=(TextView) convertView.findViewById(R.id.vote_submit_select_text_f);
		holder.tvG=(TextView) convertView.findViewById(R.id.vote_submit_select_text_g);

		holder.title.setText("<哮喘控制问卷>");
		//listAdapter = new VoteSubmitListAdapter(mContext, dataItems.get(position).getVoteAnswers());

		holder.question.setText(dataItems.get(position).getVoteQuestion());
		holder.tvA.setText("A." + dataItems.get(position).getVoteAnswers().get(0));
		holder.tvB.setText("B." + dataItems.get(position).getVoteAnswers().get(1));
		holder.tvC.setText("C." + dataItems.get(position).getVoteAnswers().get(2));
		holder.tvD.setText("D." + dataItems.get(position).getVoteAnswers().get(3));
		holder.tvE.setText("E." + dataItems.get(position).getVoteAnswers().get(4));
		holder.tvF.setText("F." + dataItems.get(position).getVoteAnswers().get(5));
		holder.tvG.setText("G." + dataItems.get(position).getVoteAnswers().get(6));

		/**单选*/
		holder.layoutA.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				/*if(map.containsKey(position)){
					return;
				}*/
				map.put(position, true);
				System.out.println("a");
				scoreMaps.put(position, 0);

				holder.ivA.setVisibility(View.GONE);
				holder.ivA_.setVisibility(View.VISIBLE);
				holder.tvA.setTextColor(mContext.getResources().getColor(R.color.theme_background));

				holder.ivB.setVisibility(View.VISIBLE);
				holder.ivB_.setVisibility(View.GONE);
				holder.tvB.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));

				holder.ivC.setVisibility(View.VISIBLE);
				holder.ivC_.setVisibility(View.GONE);
				holder.tvC.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));

				holder.ivD.setVisibility(View.VISIBLE);
				holder.ivD_.setVisibility(View.GONE);
				holder.tvD.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));

				holder.ivE.setVisibility(View.VISIBLE);
				holder.ivE_.setVisibility(View.GONE);
				holder.tvE.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));

				holder.ivF.setVisibility(View.VISIBLE);
				holder.ivF_.setVisibility(View.GONE);
				holder.tvF.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));

				holder.ivG.setVisibility(View.VISIBLE);
				holder.ivG_.setVisibility(View.GONE);
				holder.tvG.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));

				//mContext.setContentView(position+1);
			}
		});
		holder.layoutB.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				System.out.println("b");
				/*if(map.containsKey(position)){

				}*/
				map.put(position, true);
				scoreMaps.put(position, 1);


				holder.ivB.setVisibility(View.GONE);
				holder.ivB_.setVisibility(View.VISIBLE);
				holder.tvB.setTextColor(mContext.getResources().getColor(R.color.theme_background));

				holder.ivA.setVisibility(View.VISIBLE);
				holder.ivA_.setVisibility(View.GONE);
				holder.tvA.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));

				holder.ivC.setVisibility(View.VISIBLE);
				holder.ivC_.setVisibility(View.GONE);
				holder.tvC.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));

				holder.ivD.setVisibility(View.VISIBLE);
				holder.ivD_.setVisibility(View.GONE);
				holder.tvD.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));

				holder.ivE.setVisibility(View.VISIBLE);
				holder.ivE_.setVisibility(View.GONE);
				holder.tvE.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));

				holder.ivF.setVisibility(View.VISIBLE);
				holder.ivF_.setVisibility(View.GONE);
				holder.tvF.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));

				holder.ivG.setVisibility(View.VISIBLE);
				holder.ivG_.setVisibility(View.GONE);
				holder.tvG.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));
				//mContext.setContentView(position+1);
			}
		});
		holder.layoutC.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				System.out.println("c");
				/*if(map.containsKey(position)){
					return;
				}*/
				map.put(position, true);
				scoreMaps.put(position, 2);


				holder.ivC.setVisibility(View.GONE);
				holder.ivC_.setVisibility(View.VISIBLE);
				holder.tvC.setTextColor(mContext.getResources().getColor(R.color.theme_background));

				holder.ivA.setVisibility(View.VISIBLE);
				holder.ivA_.setVisibility(View.GONE);
				holder.tvA.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));

				holder.ivB.setVisibility(View.VISIBLE);
				holder.ivB_.setVisibility(View.GONE);
				holder.tvB.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));

				holder.ivD.setVisibility(View.VISIBLE);
				holder.ivD_.setVisibility(View.GONE);
				holder.tvD.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));

				holder.ivE.setVisibility(View.VISIBLE);
				holder.ivE_.setVisibility(View.GONE);
				holder.tvE.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));

				holder.ivF.setVisibility(View.VISIBLE);
				holder.ivF_.setVisibility(View.GONE);
				holder.tvF.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));

				holder.ivG.setVisibility(View.VISIBLE);
				holder.ivG_.setVisibility(View.GONE);
				holder.tvG.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));
				//mContext.setContentView(position+1);
			}
		});
		holder.layoutD.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				System.out.println("d");
				/*if(map.containsKey(position)){
					return;
				}*/
				map.put(position, true);
				scoreMaps.put(position, 3);

				holder.ivD.setVisibility(View.GONE);
				holder.ivD_.setVisibility(View.VISIBLE);
				holder.tvD.setTextColor(mContext.getResources().getColor(R.color.theme_background));

				holder.ivA.setVisibility(View.VISIBLE);
				holder.ivA_.setVisibility(View.GONE);
				holder.tvA.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));

				holder.ivB.setVisibility(View.VISIBLE);
				holder.ivB_.setVisibility(View.GONE);
				holder.tvB.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));

				holder.ivC.setVisibility(View.VISIBLE);
				holder.ivC_.setVisibility(View.GONE);
				holder.tvC.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));

				holder.ivE.setVisibility(View.VISIBLE);
				holder.ivE_.setVisibility(View.GONE);
				holder.tvE.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));

				holder.ivF.setVisibility(View.VISIBLE);
				holder.ivF_.setVisibility(View.GONE);
				holder.tvF.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));

				holder.ivG.setVisibility(View.VISIBLE);
				holder.ivG_.setVisibility(View.GONE);
				holder.tvG.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));
				//mContext.setContentView(position+1);
			}
		});
		holder.layoutE.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				System.out.println("e");
				/*if(map.containsKey(position)){
					return;
				}*/
				map.put(position, true);
				scoreMaps.put(position, 4);

				holder.ivE.setVisibility(View.GONE);
				holder.ivE_.setVisibility(View.VISIBLE);
				holder.tvE.setTextColor(mContext.getResources().getColor(R.color.theme_background));

				holder.ivA.setVisibility(View.VISIBLE);
				holder.ivA_.setVisibility(View.GONE);
				holder.tvA.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));

				holder.ivB.setVisibility(View.VISIBLE);
				holder.ivB_.setVisibility(View.GONE);
				holder.tvB.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));

				holder.ivC.setVisibility(View.VISIBLE);
				holder.ivC_.setVisibility(View.GONE);
				holder.tvC.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));

				holder.ivD.setVisibility(View.VISIBLE);
				holder.ivD_.setVisibility(View.GONE);
				holder.tvD.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));

				holder.ivF.setVisibility(View.VISIBLE);
				holder.ivF_.setVisibility(View.GONE);
				holder.tvF.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));

				holder.ivG.setVisibility(View.VISIBLE);
				holder.ivG_.setVisibility(View.GONE);
				holder.tvG.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));
				//mContext.setContentView(position+1);
			}
		});
		holder.layoutF.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				System.out.println("f");
				/*if(map.containsKey(position)){
					return;
				}*/
				map.put(position, true);
				scoreMaps.put(position, 5);

				holder.ivF.setVisibility(View.GONE);
				holder.ivF_.setVisibility(View.VISIBLE);
				holder.tvF.setTextColor(mContext.getResources().getColor(R.color.theme_background));

				holder.ivA.setVisibility(View.VISIBLE);
				holder.ivA_.setVisibility(View.GONE);
				holder.tvA.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));

				holder.ivB.setVisibility(View.VISIBLE);
				holder.ivB_.setVisibility(View.GONE);
				holder.tvB.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));

				holder.ivC.setVisibility(View.VISIBLE);
				holder.ivC_.setVisibility(View.GONE);
				holder.tvC.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));

				holder.ivD.setVisibility(View.VISIBLE);
				holder.ivD_.setVisibility(View.GONE);
				holder.tvD.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));

				holder.ivE.setVisibility(View.VISIBLE);
				holder.ivE_.setVisibility(View.GONE);
				holder.tvE.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));

				holder.ivG.setVisibility(View.VISIBLE);
				holder.ivG_.setVisibility(View.GONE);
				holder.tvG.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));
				//mContext.setContentView(position+1);
			}
		});
		holder.layoutG.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				System.out.println("g");
				/*if(map.containsKey(position)){
					return;
				}*/
				map.put(position, true);
				scoreMaps.put(position, 6);


				holder.ivG.setVisibility(View.GONE);
				holder.ivG_.setVisibility(View.VISIBLE);
				holder.tvG.setTextColor(mContext.getResources().getColor(R.color.theme_background));

				holder.ivA.setVisibility(View.VISIBLE);
				holder.ivA_.setVisibility(View.GONE);
				holder.tvA.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));

				holder.ivB.setVisibility(View.VISIBLE);
				holder.ivB_.setVisibility(View.GONE);
				holder.tvB.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));

				holder.ivC.setVisibility(View.VISIBLE);
				holder.ivC_.setVisibility(View.GONE);
				holder.tvC.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));

				holder.ivD.setVisibility(View.VISIBLE);
				holder.ivD_.setVisibility(View.GONE);
				holder.tvD.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));

				holder.ivE.setVisibility(View.VISIBLE);
				holder.ivE_.setVisibility(View.GONE);
				holder.tvE.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));

				holder.ivF.setVisibility(View.VISIBLE);
				holder.ivF_.setVisibility(View.GONE);
				holder.tvF.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_text));	
				//mContext.setContentView(position+1);
			}
		});

		// 第一页隐藏"上一步"按钮
		if (position == 0) {
			holder.previousBtn.setVisibility(View.GONE);
		} else {
			//holder.previousBtn.setVisibility(View.GONE);
			holder.previousBtn.setVisibility(View.VISIBLE);
			holder.previousBtn.setOnClickListener(new LinearOnClickListener((position - 1),false,position));
		}
		// 最后一页修改"下一步"按钮文字
		if (position == viewItems.size() - 1) {
			holder.nextText.setText("提    交");
			holder.nextImage.setImageResource(R.drawable.vote_submit_finish);

		}
		holder.nextBtn.setOnClickListener(new LinearOnClickListener((position + 1),true,position));
		container.addView(viewItems.get(position));
		return viewItems.get(position);
	}

	/**
	 *  设置上一步和下一步按钮监听
	 * 
	 */
	class LinearOnClickListener implements OnClickListener {

		private int mPosition;
		private int mPosition1;
		private boolean mIsNext;

		public LinearOnClickListener(int position,boolean mIsNext,int position1) {
			mPosition = position;
			mPosition1 = position1;
			this.mIsNext = mIsNext;

		}

		@Override
		public void onClick(View v) {
			int s=0;
			if (mPosition == viewItems.size()) {
				System.out.println("scoreMaps4=="+scoreMaps);
				if(mIsNext){
					if(!map.containsKey(mPosition1)){
						Toast.makeText(mContext, "请选择选项", Toast.LENGTH_SHORT).show();
						return;
					}
				}

				if(scoreMaps.size()>=5){
					scoreArr[4]=scoreMaps.get(mPosition-1);
				}
				System.out.println("4-mPosition="+mPosition);
				mContext.setCurrentView(mPosition);

				Toast.makeText(mContext, "感谢您完成问卷调查!", Toast.LENGTH_SHORT).show();
				System.out.println("scoreArr="+scoreArr);
				for (int i=0;i<scoreArr.length;i++) {
					// System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
					mContext.setCurrentScore(scoreArr[i]);
				}
				System.out.println("scoresItems=="+scoresItems);
				for(int i = 0;i<scoresItems.size();i++){
					s=s+scoresItems.get(i);
				}
				double averageScore = (double)s/scoresItems.size();
				System.out.println("averageScore="+averageScore);
				if(averageScore<0.75){
					scoresItems.clear();

					//DBUtil.getInstance(mContext).deleteDataToScoreTable(null, null);
					//Toast.makeText(mContext, "评估为-->哮喘控制", Toast.LENGTH_SHORT).show();
					Intent intent=new Intent(mContext,AlertDialog.class);
					intent.putExtra("msg", "评估分数："+averageScore+"\n"+"评估结果：哮喘控制");
					intent.putExtra("title", "调查问卷的结果");
					mContext.startActivity(intent);

					/*AlertDialog dialog=new AlertDialog.Builder(mContext)
					.setTitle("调查问卷的结果")
					.setMessage("评估为-->哮喘控制")
					.create();
					dialog.show();*/
				}else if(averageScore>1.5){
					scoresItems.clear();

					//DBUtil.getInstance(mContext).deleteDataToScoreTable(null, null);
					Intent intent=new Intent(mContext,AlertDialog.class);
					intent.putExtra("msg", "评估分数："+averageScore+"\n"+"评估结果：哮喘未控制");
					intent.putExtra("title", "调查问卷的结果");
					mContext.startActivity(intent);

				}else{
					scoresItems.clear();

					Intent intent=new Intent(mContext,AlertDialog.class);
					intent.putExtra("msg", "评估分数："+averageScore+"\n"+"评估结果：哮喘良好控制");
					intent.putExtra("title", "调查问卷的结果");
					mContext.startActivity(intent);

				}

			} else {
				if(mPosition==viewItems.size()-5){
					System.out.println("scoreMaps-->  -1=="+scoreMaps);
					if(mIsNext){
						if(!map.containsKey(mPosition1)){
							Toast.makeText(mContext, "请选择选项", Toast.LENGTH_SHORT).show();
							return;
						}
					}
					System.out.println("-1-mPosition="+mPosition);
					mContext.setCurrentView(mPosition);
				}

				else if(mPosition==viewItems.size()-4){
					System.out.println("scoreMaps0=="+scoreMaps);
					if(mIsNext){
						if(!map.containsKey(mPosition1)){
							Toast.makeText(mContext, "请选择选项", Toast.LENGTH_SHORT).show();
							return;
						}
					}
					/*if(scoreMaps.get("1") != null && scoreMaps.get("1")==score){
						scoreMaps.put("1", scoreMaps.get("1"));
					}*/
					//scoreMaps.put("1", score_1);
					if(scoreMaps.size()>=1){
						scoreArr[0]=scoreMaps.get(mPosition-1);
					}
					System.out.println("0-mPosition="+mPosition);
					mContext.setCurrentView(mPosition);


				}else if(mPosition==viewItems.size()-3){
					System.out.println("scoreMaps1=="+scoreMaps);
					if(mIsNext){
						if(!map.containsKey(mPosition1)){
							Toast.makeText(mContext, "请选择选项", Toast.LENGTH_SHORT).show();
							return;
						}
					}
					System.out.println("1-mPosition="+mPosition);
					mContext.setCurrentView(mPosition);
					if(scoreMaps.size()>=2){
						scoreArr[1]=scoreMaps.get(mPosition-1);
					}
					System.out.println("0-0-0-0-0-0-");



				}else if(mPosition==viewItems.size()-2){
					System.out.println("scoreMaps2=="+scoreMaps);
					if(mIsNext){
						if(!map.containsKey(mPosition1)){
							Toast.makeText(mContext, "请选择选项", Toast.LENGTH_SHORT).show();
							return;
						}
					}
					if(scoreMaps.size()>=3){
						scoreArr[2]=scoreMaps.get(mPosition-1);
					}
					System.out.println("2-mPosition="+mPosition);
					mContext.setCurrentView(mPosition);


				}else if(mPosition==viewItems.size()-1){
					System.out.println("scoreMaps3=="+scoreMaps);
					if(mIsNext){
						if(!map.containsKey(mPosition1)){
							Toast.makeText(mContext, "请选择选项", Toast.LENGTH_SHORT).show();
							return;
						}
					}
					if(scoreMaps.size()>=4){
						scoreArr[3]=scoreMaps.get(mPosition-1);
					}
					System.out.println("3-mPosition="+mPosition);
					mContext.setCurrentView(mPosition);

				}
			}
		}
	}




	@Override
	public int getCount() {
		if (viewItems == null)
			return 0;
		return viewItems.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	/**
	 *  自定义类
	 */
	class ViewHolder {
		ListView listView;
		TextView title;
		TextView question;
		TextView answer;
		LinearLayout previousBtn, nextBtn;
		TextView nextText;
		ImageView nextImage;

		//...
		LinearLayout layoutA;
		LinearLayout layoutB;
		LinearLayout layoutC;
		LinearLayout layoutD;
		LinearLayout layoutE;
		LinearLayout layoutF;
		LinearLayout layoutG;


		ImageView ivA;
		ImageView ivB;
		ImageView ivC;
		ImageView ivD;
		ImageView ivE;
		ImageView ivF;
		ImageView ivG;

		ImageView ivA_;
		ImageView ivB_;
		ImageView ivC_;
		ImageView ivD_;
		ImageView ivE_;
		ImageView ivF_;
		ImageView ivG_;

		TextView tvA;
		TextView tvB;
		TextView tvC;
		TextView tvD;
		TextView tvE;
		TextView tvF;
		TextView tvG;


	}


}
