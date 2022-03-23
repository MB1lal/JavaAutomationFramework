package frontend.steps;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseSteps {
    public utils.ExcelReader excelReader = utils.ExcelReader.getInstance();
    public static Logger logger = LoggerFactory.getLogger(BaseSteps.class);

}
