package model;

public class PlayingMovieVO {
	int pm_no;
	String movie_title;
	int pm_sheetNum;
	String pm_startTime;

	public PlayingMovieVO(int pm_no, String movie_title, int pm_sheetNum, String pm_startTime) {
		super();
		this.pm_no = pm_no;
		this.movie_title = movie_title;
		this.pm_sheetNum = pm_sheetNum;
		this.pm_startTime = pm_startTime;
	}

	public int getPm_no() {
		return pm_no;
	}

	public String getMovie_title() {
		return movie_title;
	}

	public int getPm_sheetNum() {
		return pm_sheetNum;
	}

	public String getPm_startTime() {
		return pm_startTime;
	}

}
