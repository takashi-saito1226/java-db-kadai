package kadai_010;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Scores_Chapter10 {

	public static void main(String[] args) {
		Connection con = null;
		Statement statement = null;
		
		try {
			
			con = DriverManager.getConnection("jdbc:mysql://localhost/challenge_java","root","taka3829");
			
			System.out.println("データベース接続成功" + con.toString());
			
			statement = con.createStatement();
			
			String sql = "UPDATE scores SET score_math = 95,score_english = 80 WHERE id = 5;";
			int rowCnt = statement.executeUpdate(sql);
			System.out.println(rowCnt + "件のレコードが更新されました");
			
			String sql2 = "SELECT * FROM scores ORDER BY score_math DESC, score_english DESC;";
			
			System.out.println("データを取得" + sql2);
			ResultSet result = statement.executeQuery(sql2);
			
			while(result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				int scoreMath = result.getInt("score_math");
				int scoreEnglishI = result.getInt("score_english");
				System.out.println(result.getRow()+"件目:生徒id=" + id + "/氏名=" + name + "/数学=" + scoreMath + "/英語=" + scoreEnglishI);
				
				
			}
		}catch(SQLException e) {
			
			System.out.println("エラー発生" + e.getMessage());
			
		}finally {
			
			if(statement != null) {
				
				try {statement.close();}catch(SQLException ignore) {}
				
			}if(con != null) {
				try {con.close();}catch(SQLException ignore) {}
			}
			
		}
		
	}

}