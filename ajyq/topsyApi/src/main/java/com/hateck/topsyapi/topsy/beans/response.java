package com.hateck.topsyapi.topsy.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 15/4/10.
 */
public class response {
    private int perpage;
    private int a;
    private int h;
    private int m;
    private int d;
    private int w;
    public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	private List<list> list = new ArrayList<list>();

    public int getPerpage() {
        return perpage;
    }

    public void setPerpage(int perpage) {
        this.perpage = perpage;
    }

    public List<list> getList() {
        return list;
    }

    public void setList(List<list> list) {
        this.list = list;
    }
}
