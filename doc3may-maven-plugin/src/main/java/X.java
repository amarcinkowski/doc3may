
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.github.amarcinkowski.doc3may.annotations.ApiOperation;

class X {
	
//	QQww qw;
//	Zxc zxc;
	
	@ApiOperation(value="abc", notes = "notes1 tralalaa")
	@RequestMapping(value="path1", method=RequestMethod.GET)
	void methXXX() {
//		Err.q();
		}
	// here's an error
//	zxc
	
	@RequestMapping(value="/qwe", method=RequestMethod.POST)
	@ApiOperation(value="this is qwe", notes = "and qwe's ontes")
	void qweZXC() {
//		Zxc.w();
		}
}