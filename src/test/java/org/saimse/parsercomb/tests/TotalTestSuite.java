package org.saimse.parsercomb.tests;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({BaseParsers.class, Combinators.class})
public class TotalTestSuite {}
