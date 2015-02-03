package org.decimal4j.op;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.decimal4j.api.Decimal;
import org.decimal4j.api.DecimalArithmetic;
import org.decimal4j.scale.ScaleMetrics;
import org.decimal4j.test.TestSettings;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Unit test for {@link Decimal#abs()}
 */
@RunWith(Parameterized.class)
public class ToStringTest extends Abstract1DecimalArgToAnyResultTest<String> {
	
	public ToStringTest(ScaleMetrics scaleMetrics, RoundingMode roundingMode, DecimalArithmetic arithmetic) {
		super(arithmetic);
	}

	@Parameters(name = "{index}: scale={0}, rounding={1}")
	public static Iterable<Object[]> data() {
		final List<Object[]> data = new ArrayList<Object[]>();
		for (final ScaleMetrics s : TestSettings.SCALES) {
			data.add(new Object[] {s, RoundingMode.DOWN, s.getArithmetic(RoundingMode.DOWN)});
		}
		return data;
	}

	@Override
	protected String operation() {
		return "toString";
	}
	
	@Override
	protected String expectedResult(BigDecimal operand) {
		return operand.toPlainString();
	}
	
	@Override
	protected <S extends ScaleMetrics> String actualResult(Decimal<S> operand) {
		return operand.toString();
	}
}
