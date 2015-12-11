package com.rilintech.fragment_301_huxike_android.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.TextView;

import com.rilintech.fragment_301_huxike_android.R;
import com.rilintech.fragment_301_huxike_android.adapter.VoteSubmitAdapter;
import com.rilintech.fragment_301_huxike_android.bean.VoteSubmitItem;
import com.rilintech.fragment_301_huxike_android.tool.DataLoader;
import com.rilintech.fragment_301_huxike_android.tool.ViewPagerScroller;
import com.rilintech.fragment_301_huxike_android.viewpager.VoteSubmitViewPager;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class QuestionnaireActivity extends BaseActivity {
	VoteSubmitViewPager viewPager;
	VoteSubmitAdapter pagerAdapter;
	List<View> viewItems = new ArrayList<View>();
	ArrayList<VoteSubmitItem> dataItems = new ArrayList<VoteSubmitItem>();
	ArrayList<Integer> scoresItems=new ArrayList<Integer>();

	private TextView tv_title;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.questionnaire);
		init();
	}
	/**
	 * 页面初始化
	 */
	private void init() {

		tv_title = (TextView) findViewById(R.id.layout_title).findViewById(R.id.tv_title);
		tv_title.setText(getResources().getString(R.string.questionnaire));

		dataItems = new DataLoader(this).getTestData();
		for (int i = 0; i < dataItems.size(); i++) {
			viewItems.add(getLayoutInflater().inflate(R.layout.vote_submit_viewpager_item, null));
		}
		
		viewPager = (VoteSubmitViewPager) findViewById(R.id.vote_submit_viewpager);
		pagerAdapter = new VoteSubmitAdapter(this, viewItems, dataItems,scoresItems);
		viewPager.setAdapter(pagerAdapter);
		viewPager.getParent().requestDisallowInterceptTouchEvent(false);
		initViewPagerScroll();
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			/**
			 * 页面切换后调用 
			 * position  新的页面位置
			 */
			@Override
			public void onPageSelected(int position) {
				viewPager.setCurrentItem(position);

			}

			/**
			 * 页面正在滑动的时候，回调
			 */
			@Override
			public void onPageScrolled(int position, float positionOffset,
									   int positionOffsetPixels) {

			}

			/**
			 * 当页面状态发生变化的时候，回调
			 */
			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
	}
	/**
     * 设置ViewPager的滑动速度
     * 
     * */
    private void initViewPagerScroll( ){
    try {
            Field mScroller = null;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true); 
            ViewPagerScroller scroller = new ViewPagerScroller(viewPager.getContext());
            mScroller.set(viewPager, scroller);
        }catch(NoSuchFieldException e){
       
        }catch (IllegalArgumentException e){
       
        }catch (IllegalAccessException e){
       
        }
    }
	/**
	 * @param index
	 *            根据索引值切换页面
	 */
	public void setCurrentView(int index) {
		viewPager.setCurrentItem(index);

	}
	public void setCurrentScore(Integer score){
		scoresItems.add(score);
	}


	public void onClick(View view){
		this.finish();
	}
}
