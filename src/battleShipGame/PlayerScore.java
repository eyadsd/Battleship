package battleShipGame;

public class PlayerScore {
	String playerName;
	int wins;
	int losses;
	double winrate;
	
	public PlayerScore()
	{
		playerName = "player";
		wins = 0;
		losses = 0;
		winrate = (double)wins/(wins+losses);
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public int getLosses() {
		return losses;
	}
	public void setLosses(int losses) {
		this.losses = losses;
	}
	public double getWinrate() {
		return winrate;
	}
	public void setWinrate(double winrate) {
		this.winrate = winrate;
	}
	
	

}
