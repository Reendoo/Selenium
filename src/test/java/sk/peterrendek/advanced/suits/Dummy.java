package sk.peterrendek.advanced.suits;

import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("sk.peterrendek.advanced.tests")
//@IncludeTags("Sincity")
@IncludeTags({"Blbosti"})
public class Dummy {
}
