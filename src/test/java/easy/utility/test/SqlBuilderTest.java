package easy.utility.test;

import org.junit.*;

import easy.utility.SqlBuilder;

public class SqlBuilderTest {
	@Test
	public void hasConditionTest() {
		SqlBuilder builder = new SqlBuilder(true);

		builder.append(true, "and", "a=@id");
		builder.append(false, "and", "b=@b");
		builder.append(true, "and", "c=@c");

		String sql = builder.toSqlString();

		Assert.assertEquals("WHERE a=@id and c=@c", sql);
	}

	@Test
	public void noConditionTest() {
		SqlBuilder builder = new SqlBuilder(true);

		builder.append(false, "and", "a=@id");
		builder.append(false, "and", "b=@b");
		builder.append(false, "and", "c=@c");

		String sql = builder.toSqlString();

		Assert.assertEquals("", sql);
	}
	@Test
	public void noFirstConditionTest(){
		SqlBuilder builder = new SqlBuilder(true);

		builder.append(false, "and", "a=@id");
		builder.append(true, "and", "b=@b");
		builder.append(false, "and", "c=@c");

		String sql = builder.toSqlString();

		Assert.assertEquals("WHERE b=@b", sql);
	}
}
