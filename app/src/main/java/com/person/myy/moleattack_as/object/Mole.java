package com.person.myy.moleattack_as.object;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.person.myy.moleattack_as.GameSize;
import com.person.myy.moleattack_as.GameSurfaceView;
import com.person.myy.moleattack_as.collection.TurnipCollection;

import java.util.Random;

public class Mole {

	public static enum ACT {
		UP, DOWN, STAND, DIE
	};

	public static final float CAMP_H = GameSize.getNewY(60);//攀爬的最高高度
	private static final float DIE_DURATION = 20, IN_SPE = GameSize.getNewY(1);//初始上升速度
	private static final float SPE_ADD = GameSize.getNewX(0.1f), MAX_SPE = GameSize.getNewX(3);
	private Random random = new Random();
	public int loact;
	public boolean islive;
	// 地鼠的绘画坐标，图片长宽
	private float y,x, m_w, m_h;// 竖向速度变化需要更精确所以需要y为float
	private static float spe = IN_SPE;
	private Bitmap mole_bmp_live;
	private Bitmap mole_bmp_die;
	private Hole hole;
	private ACT state;
	private int die_time;// 记录当前已死亡的时间
	// 剪切方块的坐标
	private float clip_r_x1, clip_r_x2, clip_r_y1, clip_r_y2;
	public boolean eat_flag;
	private GameSurfaceView gsv;

	public Mole(Hole hole, Bitmap mole_bmp_live, Bitmap mole_bmp_die,
			GameSurfaceView gsv) {

		this.mole_bmp_live = mole_bmp_live;
		this.mole_bmp_die = mole_bmp_die;
		this.hole = hole;
		this.gsv = gsv;
		// x,y通过hole的x，y来确定
		m_w = mole_bmp_live.getWidth();
		m_h = mole_bmp_live.getHeight();
		x = hole.getX() + hole.getH_W() / 2 - m_w / 2;
		y = hole.getY() + hole.getH_H() / 2;
		clip_r_x1 = x;
		clip_r_y1 = hole.getY() + hole.getH_H() / 2 - (m_h * 2 / 3);
		clip_r_x2 = x + m_w;
		clip_r_y2 = hole.getY() + hole.getH_H() / 2;

		islive = true;
		state = ACT.STAND;
		die_time = 0;
		eat_flag = false;
	}

	public void draw(Canvas canvas, Paint paint) {
		if (state != ACT.DIE) {
			canvas.save();
			canvas.clipRect(clip_r_x1, clip_r_y1, clip_r_x2, clip_r_y2);
			canvas.drawBitmap(mole_bmp_live, x, y, paint);
			canvas.restore();
			// canvas.drawRect(x,y,clip_r_x2,clip_r_y2, paint);
		} else {
			canvas.save();
			canvas.clipRect(clip_r_x1, clip_r_y1, clip_r_x2, clip_r_y2);
			canvas.drawBitmap(mole_bmp_die, x, y, paint);
			canvas.restore();
		}

		// int color = paint.getColor();
		// paint.setColor(Color.WHITE);
		// canvas.drawRect(clip_r_x1, clip_r_y1, clip_r_x2, clip_r_y2, paint);
		// paint.setColor(Color.WHITE);
		// canvas.drawRect(x,y,x+m_w,y+m_h, paint);
		// paint.setColor(Color.RED);
		// canvas.drawRect(hole.getX(), hole.getY(), hole.getX()+hole.getH_W(),
		// hole.getY()+hole.getH_H(), paint);
		// Log.i("myy.mole", x+" "+ y+" "+
		// (x+m_w)+" "+(y+m_h)+" "+state);
		// paint.setColor(color);
	}

	public void logic() {
		if (state == ACT.UP) {
			if (y - spe >= clip_r_y1) {
				y -= spe;
			} else {
				y = clip_r_y1;
				state = ACT.DOWN;// 到达顶端就开始下降
			}
			// Log.i("myy.mole", "地鼠在上爬 x="+x+" y="+y);
		} else if (state == ACT.DOWN) {
			if (y + spe <= clip_r_y2) {
				y += spe;
			} else {
				y = clip_r_y2;
				state = ACT.STAND;// 下降完成就静止
				TurnipCollection.reduce();
			}
			// Log.i("myy.mole", "地鼠在下降 x="+x+" y="+y);
		} else if (state == ACT.DIE) {
			if (islive == true) {
				if (die_time <= DIE_DURATION) {
					die_time++;
				} else {
					die_time = 0;
					y = clip_r_y2;
					// islive=false;
					state = ACT.STAND;// 死亡时间满后静止
				}
			}
			// Log.i("myy.mole", "地鼠死了 x="+x+" y="+y);
		} else if (state == ACT.STAND) {
			// 静止不动
			// Log.i("myy.mole", "地鼠静止 x="+x+" y="+y);
		}
		clip_r_x1 = x;
		clip_r_y1 = hole.getY() + hole.getH_H() / 2 - (m_h * 2 / 3);
		clip_r_x2 = x + m_w;
		clip_r_y2 = hole.getY() + hole.getH_H() / 2;
	}

	public void die() {
		setState(ACT.DIE);
		play();
	}

	public void play() {
		gsv.gs_collection.play("BEHIT");
	}

	public void setState(ACT state) {
		this.state = state;
	}

	public ACT getState() {
		return state;
	}

	/**
	 * 重新设置地鼠状态
	 */
	public void reset() {
		y = clip_r_y2;
		state = ACT.STAND;
	}

	public Rect getTouchRect() {
		Rect rect = new Rect((int)x,(int)y,(int)clip_r_x2,(int)clip_r_y2);
		return rect;
	}

	public static void increaSpe() {
		if (spe + SPE_ADD <= MAX_SPE) {
			spe += SPE_ADD;
		}
//		 Log.i("Mole","当前速度"+spe);
//		Toast.makeText(null,"当前速度"+spe,1);
	}

	public static void resetSpe() {
		spe = IN_SPE;
	}
}
