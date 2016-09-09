package easy.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SqlBuilder {
	private final List<SqlSegment> sqlSegments = new ArrayList<>();

	private static final SqlSegment WHERESQL = new SqlSegment(true, "WHERE");

	public SqlBuilder(boolean appendWhere) {
		if (appendWhere) {
			sqlSegments.add(WHERESQL);
		}
	}
	
	public void append(java.util.function.Supplier<Boolean> condition,String prepend,String sql){
		this.append(condition.get(), prepend,sql);
	}

	public void append(boolean condition, String prepend, String sql) {
		if (condition) {

			if (!sqlSegments.get(sqlSegments.size() - 1).sql.equals("WHERE")) {
				sqlSegments.add(new SqlSegment(true, prepend));
			}
			sqlSegments.add(new SqlSegment(true, sql));
		}
	}

	public String toSqlString() {

		if (sqlSegments.size() == 1 && sqlSegments.get(0).sql.equals("WHERE")) {
			return "";
		}

		return this.sqlSegments.stream().filter(s -> s.isAppend)
				.map(s -> s.sql).collect(Collectors.joining(" ")).trim();

	}
}

class SqlSegment {
	public boolean isAppend;
	public String sql;

	public SqlSegment(boolean isAppend, String sql) {
		this.isAppend = isAppend;
		this.sql = sql;
	}

}
