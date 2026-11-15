/**
 * Generated from IDL interface "Calculator".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 15 Nov 2026, 1:47:01 pm
 */

public final class CalculatorHolder	implements org.omg.CORBA.portable.Streamable{
	 public Calculator value;
	public CalculatorHolder()
	{
	}
	public CalculatorHolder (final Calculator initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return CalculatorHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = CalculatorHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		CalculatorHelper.write (_out,value);
	}
}
