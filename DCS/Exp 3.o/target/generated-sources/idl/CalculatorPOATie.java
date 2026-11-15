import org.omg.PortableServer.POA;
/**
 * Generated from IDL interface "Calculator".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 15 Nov 2026, 1:47:01 pm
 */

public class CalculatorPOATie
	extends CalculatorPOA
{
	private CalculatorOperations _delegate;

	private POA _poa;
	public CalculatorPOATie(CalculatorOperations delegate)
	{
		_delegate = delegate;
	}
	public CalculatorPOATie(CalculatorOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public Calculator _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		Calculator __r = CalculatorHelper.narrow(__o);
		return __r;
	}
	public Calculator _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		Calculator __r = CalculatorHelper.narrow(__o);
		return __r;
	}
	public CalculatorOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(CalculatorOperations delegate)
	{
		_delegate = delegate;
	}
	public POA _default_POA()
	{
		if (_poa != null)
		{
			return _poa;
		}
		return super._default_POA();
	}
	public int add(int a, int b)
	{
		return _delegate.add(a,b);
	}

}
