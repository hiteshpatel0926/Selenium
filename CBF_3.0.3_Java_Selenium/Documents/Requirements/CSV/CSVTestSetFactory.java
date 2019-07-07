/******************************************************************************
$Id : CSVTestSetFactory.java 06/29/2017 4:08:37 PM
Copyright © 2016 Capgemini Group of companies. All rights reserved
(Subject to Limited Distribution and Restricted Disclosure Only.)
THIS SOURCE FILE MAY CONTAIN INFORMATION WHICH IS THE PROPRIETARY
INFORMATION OF CAPGEMINI GROUP OF COMPANIES AND IS INTENDED FOR USE
ONLY BY THE ENTITY WHO IS ENTITLED TO AND MAY CONTAIN
INFORMATION THAT IS PRIVILEGED, CONFIDENTIAL, OR EXEMPT FROM
DISCLOSURE UNDER APPLICABLE LAW.
YOUR ACCESS TO THIS SOURCE FILE IS GOVERNED BY THE TERMS AND
CONDITIONS OF AN AGREEMENT BETWEEN YOU AND CAPGEMINI GROUP OF COMPANIES.
The USE, DISCLOSURE REPRODUCTION OR TRANSFER OF THIS PROGRAM IS
RESTRICTED AS SET FORTH THEREIN.
******************************************************************************/
package cbfx.factory.test;

import java.util.List;
import java.util.Map;

import cbf.model.TestInstance;
import cbf.model.TestSet;
import cbf.utils.DTAccess;
import cbf.utils.TypeUtils;
import cbfx.factory.test.BaseTestSetFactory.BaseTestInstance;

public class CSVTestSetFactory extends BaseTestSetFactory {
	/**
	 * Plugin constructor
	 * @param params
	 */
	public CSVTestSetFactory(Map<String, String> params) {
	  super(params);
  }
	/**
	 * Main method which returns the testset for a given options
	 * @param info: contains testSetFile/testSetSheet names
	 * TODO: defaults
	 */
	 public TestSet getTestSet(Map info){
		 Map tsparams= mergeWithParams(info);
		 
		 final List<Map> instanceData= readCSVFile(tsparams);
		 return new TestSet() {
			    public int testInstanceCount() {
				    return instanceData.size();
			    }
			    public TestInstance testInstance(final int ix) {
			        final Map data = instanceData.get(ix);
			        return new BaseTestInstance(data) {
			          public String instanceName() {
								  return (String) data.get("TestCase Name");
			          };
			        };
			      }
			    };
	 }
     private List<Map> readCSVFile(Map params){
		 String fileName=null;
		 try{
			 fileName=labFolder+"/"+(String) params.get("testSetFile");
		 }catch(ClassCastException e){
			 logger.handleError("Invalid CSV file configuration",params,e);
		 }
		 
		 try{
			 DTAccess dtAccess= new DTAccess(fileName);
			 return dtAccess.readSelectedRows(FILTER_SELECTYN);
		 }catch(Exception e){
			 logger.handleError("Reading testset file",fileName,e);
		 }
		 return null;
	 }
	 
	 private static final DTAccess.RowSelector FILTER_SELECTYN = 
    new DTAccess.RowSelector() {
    public boolean select(Map row, int rowIx) {
				return TypeUtils.string2Bool(((String) row.get("SelectYN"))); 
    }
  };
}
	