package org.jfree.data.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.jfree.data.time.Quarter;
import org.jfree.data.time.Year;
import org.junit.Test;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class QuarterClassTest 
{
    Quarter quarter;
    Calendar calendar; 
    
    private void arrange() {
        quarter = new Quarter();
    }
    
	private void arrange(Integer quart, Integer year) {
        quarter = new Quarter(quart, year);
    }
	
	private void arrange(Integer quart, Year year) {
        quarter = new Quarter(quart, year);
    }

	private void arrange(Date time, TimeZone zone) {
        quarter = new Quarter(time, zone);
    }
	
	private void arrange(Date time) {
        quarter = new Quarter(time);
    }
    
    
    private void arrange(int year , int month , int day , int hour, int minute , int sec , int milliSec) {
        calendar = Calendar.getInstance() ;
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH,month); // months are 0-based (0=January, 1=February, etc.)
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, sec);
        calendar.set(Calendar.MILLISECOND, milliSec);
    }
    
    @Test
    public void testQuarterDefaultCtor() {
        arrange();
        assertEquals(2023, quarter.getYear().getYear());
        assertEquals(2, quarter.getQuarter());
    }
    
    //Constructor (Integer, Integer)
    
    @Test
    public void testQuarterCtor1() {
    	Integer quart=2;
    	Integer year=1950;
    	
        arrange(quart, year);
        assertEquals(1950, quarter.getYear().getYear());
        assertEquals(2, quarter.getQuarter());
    } 

    @Test
    public void testQuarterCtor2() {
    	Integer quart=1;
    	Integer year=1900;
    	
        arrange(quart, year);
        assertEquals(1900, quarter.getYear().getYear());
        assertEquals(1, quarter.getQuarter());
    }
    
   
    @Test
    public void testQuarterCtor3() {
    	Integer quart=4;
    	Integer year=9999;
    	
        arrange(quart, year);
        assertEquals(9999, quarter.getYear().getYear());
        assertEquals(4, quarter.getQuarter());
    }
    
   
    @Test
    public void testQuarterCtor4() {
    	Integer quart=6;
    	Integer year=2005;
    	
        arrange(quart, year);
        assertEquals(2005, quarter.getYear().getYear());
        assertEquals(6, quarter.getQuarter());
    }
    
    
    @Test
    public void testQuarterCtor5() {
    	Integer quart=3;
    	Integer year=10000;
    	
        arrange(quart, year);
        assertEquals(10000, quarter.getYear().getYear());
        assertEquals(3, quarter.getQuarter());
    }
    
    //Constructor (Integer, Year)
    @Test
    public void testQuarterCtor6() {
    	Integer quart=3;
    	Year year = new Year(2023);
    	
        arrange(quart, year);
        assertEquals(2023, quarter.getYear().getYear());
        assertEquals(3, quarter.getQuarter());
    }
    
    @Test
    public void testQuarterCtor7() {
    	Integer quart=5;
    	Year year = new Year(2023);
    	
        arrange(quart, year);
        assertEquals(2023, quarter.getYear().getYear());
        assertEquals(4, quarter.getQuarter());
    }
    
    @Test
    public void testQuarterCtor8() {
    	Integer quart=3;
    	Year year = new Year(20000);
    	
        arrange(quart, year);
        assertEquals(20000, quarter.getYear().getYear());
        assertEquals(3, quarter.getQuarter());
    }
    
    @Test
    public void testQuarterCtor9() {
    	Integer quart=3;
    	
    	Date date = new Date();
    	TimeZone zone = TimeZone.getTimeZone("Egypt/Cairo");
    	
    	Year year = new Year(date , zone);
        arrange(quart, year);
        assertEquals(2023, quarter.getYear().getYear());
        assertEquals(3, quarter.getQuarter());
    }

    //Constructor (Date, TimeZone)
    @Test
    public void testQuarterCtor10() {
    	
    	Date time = new Date();
    	TimeZone zone = TimeZone.getDefault();
    	
        arrange(time, zone);
        
        assertEquals(2023, quarter.getYear().getYear());
        assertEquals(2, quarter.getQuarter());
    }
    
    //Constructor (Date)
    @Test
    public void testQuarterCtor11() {
    	
    	Date time = new Date();
    	
        arrange(time);
        
        assertEquals(2023, quarter.getYear().getYear());
        assertEquals(2, quarter.getQuarter());
    }
    
    
    //Methods
    @Test
    public void testCompareTO()
    {
        arrange(4,1999);
        Quarter quarter2 =new Quarter(1, 1999);

        assertEquals(3, quarter.compareTo(quarter2), 1E-13); 
        // after order succeed (in same year )
        // after quarter succeed
     
    }
    @Test
    public void testCompareTO2()
    {
        arrange(4 , 1999);
        Quarter quarter2 =new Quarter(4, 1991);

        assertEquals(8, quarter.compareTo(quarter2), 1E-13); 
        // after order succeed 
        // after year succeed
    }

    @Test
    public void testCompareTO3()
    {
        arrange(1,1999);
        Quarter quarter2 =new Quarter(4, 1999);
        
        assertEquals(-3, quarter.compareTo(quarter2), 1E-13);
        // before order not succeed 
        //before quarter succeed
    }
    @Test
    public void testCompareTO4()
    {
        arrange(1,1998);
        Quarter quarter2 =new Quarter(4, 1999);
        
        assertEquals(-1, quarter.compareTo(quarter2), 1E-13);
        // before order not succeed 
        //before quarter succeed
    }

    @Test
    public void testCompareTO5()
    {
        arrange(4,1999);
        Quarter quarter2 =new Quarter(4, 1999);
        
        assertEquals(0, quarter.compareTo(quarter2), 1E-13);
        // same order  succeed 
    }

    @Test
    public void testCompareTO6()
    {
        arrange(4,1899);
        Quarter quarter2 =new Quarter(4,1900);
        
        assertEquals(-1, quarter.compareTo(quarter2), 1E-13);
        // out of range year IllegalArgumentException (not on the report ) 

    }
    
    @Test
    public void testEqual1 ()
    {
        arrange(4,1900);
        Quarter quarter2 =new Quarter(4,1900);

        assertTrue(quarter.equals(quarter2));
    }

    @Test
    public void testEqual2 ()
    {
        arrange(1,1900);
        Quarter quarter2 =new Quarter(4,1909);

        assertFalse(quarter.equals(quarter2));
    }

    @Test
    public void testEqual3 ()
    {
        arrange(1 , 1900);
        arrange(1900 , 0 , 26 , 10 , 30 , 0 ,0);
 

        assertTrue(quarter.equals(calendar));
        // equality comparing to the calendar object failed
    }

    @Test
    public void testGetFirstMillisecond ()
    {
        arrange(2,1900);
        arrange(1900,3,1,0,0,0,0);
        calendar.setTimeZone(TimeZone.getTimeZone("Europe/London"));

        long expectedFirstMillisecond = calendar.getTimeInMillis();

        long actualFirstMillisecond = quarter.getFirstMillisecond(calendar);

        assertEquals(expectedFirstMillisecond,actualFirstMillisecond );
       
       
        // succeed 
    }

    @Test
    public void testGetFirstMillisecond2()
    {
        arrange(1,1910);
        arrange(1900,3,1,0,0,0,0);
        calendar.setTimeZone(TimeZone.getTimeZone("Europe/London"));

        long expectedFirstMillisecond = calendar.getTimeInMillis();

        long actualFirstMillisecond = quarter.getFirstMillisecond(calendar);

        assertNotEquals(expectedFirstMillisecond,actualFirstMillisecond );
       
    }
    
    @Test
    public void testGetLastMillisecond ()
    {
        
        arrange(2,1900);
        arrange(1900,5,30,23,59,59,999);
        calendar.setTimeZone(TimeZone.getTimeZone("Europe/London"));

        long expectedLastMillisecond = calendar.getTimeInMillis();

        long actualLastMillisecond = quarter.getLastMillisecond(calendar);

        assertEquals(expectedLastMillisecond,actualLastMillisecond );
       
        // failed 
    }

    @Test
    public void testGetLastMillisecond2()
    {
        Date date = new Date(1640380800000L);
        TimeZone timeZone = TimeZone.getTimeZone("Europe/London") ;
        arrange(date,timeZone);
        arrange(2021,11,25,10,30,0,0);
        calendar.setTimeZone(TimeZone.getTimeZone("Europe/London"));

        long expectedLastMillisecond = calendar.getTimeInMillis();

        long actualLastMillisecond = quarter.getLastMillisecond(calendar);

        assertNotEquals(expectedLastMillisecond,actualLastMillisecond );
       
        // failed 
    }

    
    @Test
    public void testParseQuarter1(){
        Quarter q = Quarter.parseQuarter("2019-Q3");

   
        assertEquals(3, q.getQuarter());
        assertEquals(2019, q.getYear().getYear());
        
    }
  @Test
    public void testParseQuarter2(){
        Quarter q = Quarter.parseQuarter("Q3-2019");

   
        assertEquals(3, q.getQuarter());
        assertEquals(2019, q.getYear().getYear());
        
    }

    @Test
    public void testParseQuarter3(){
        Quarter q = Quarter.parseQuarter("2019 Q3");
        
        
        assertEquals(2019, q.getYear().getYear());
        assertEquals(3, q.getQuarter());
        
    }

    @Test
    public void testParseQuarter4(){
        Quarter q = Quarter.parseQuarter("Q3 2019");
        
   
        assertEquals(3, q.getQuarter());
        
    }

    @Test
    public void testParseQuarter5(){
        Quarter q = Quarter.parseQuarter("2019/Q3");
        
   
        assertEquals(3, q.getQuarter());;
        
    }

    @Test
    public void testParseQuarter6(){
        Quarter q = Quarter.parseQuarter("Q3/2019");
        
   
        assertEquals(3, q.getQuarter());
        
    }

    @Test
    public void testParseQuarter7(){
        Quarter q = Quarter.parseQuarter("2019,Q3");
        
   
        assertEquals(3, q.getQuarter());
        
    }
    @Test
    public void testParseQuarter8(){
        Quarter q = Quarter.parseQuarter("Q3,2019");
        
   
        assertEquals(3, q.getQuarter());
        
    }
    @Test
    public void testParseQuarter9(){
        Quarter q = Quarter.parseQuarter("Q3_2019");

        assertEquals(3, q.getQuarter());
        // org.jfree.data.time.TimePeriodFormatException: Cannot parse string. 
        // way to add YYYY_Qn in invalid  
    }

    @Test
    public void testParseQuarter10(){
        Quarter q = Quarter.parseQuarter("2019_Q3");

        assertEquals(3, q.getQuarter());
         // org.jfree.data.time.TimePeriodFormatException: Cannot parse string. 
        // way to add YYYY_Qn in invalid  
    }

    @Test
    public void testParseQuarter11(){
        Quarter q = Quarter.parseQuarter("Q3");

        assertEquals(3, q.getQuarter());
         // org.jfree.data.time.TimePeriodFormatException: Cannot parse string. 
        // unhandeled exeption when parsing just Qn
    }
    @Test
    public void testParseQuarter12(){
        Quarter q = Quarter.parseQuarter("Q 2019");

        assertEquals(0, q.getQuarter());
         // org.jfree.data.time.NumberFormatException
    }
    
 
    @Test
    public void testGetQuarter() {
 
        arrange();
        
        assertEquals(2, quarter.getQuarter());
    }
    
    @Test
    public void testGetQuarter1() {
    	int quad=-1, year=2027;
 
        arrange(quad, year);
        
        assertEquals(1, quarter.getQuarter());
    }
    
    @Test
    public void testGetQuarter2() {
    	int quad=7, year=2027;
 
        arrange(quad, year);
        
        assertEquals(4, quarter.getQuarter());
    }
    @Test
    public void testGetQuarter3() {
    	int quad=1, year=2027;
 
        arrange(quad, year);
        
        assertEquals(1, quarter.getQuarter());
    }
    
    @Test
    public void testGetQuarter4() {
    	int quad=4, year=2027;
 
        arrange(quad, year);
        
        assertEquals(4, quarter.getQuarter());
    }
    
    @Test
    public void testGetYear() {
    	int quad = 2;
    	Year year = new Year(2027);
 
        arrange(quad, year);
        
        assertEquals(year, quarter.getYear());
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testGetYear1() {
    	int quad = 2;
    	Year year = new Year(20000);
 
        arrange(quad, year);
        
        assertEquals(year, quarter.getYear());
    }
    
    @Test
    public void testGetYear2() {
    	int quad = 2;
    	Year year = new Year(1000);
 
        arrange(quad, year);
        
        assertEquals(year, quarter.getYear());
    }
    
    @Test
    public void testPrevious() {
    	
    	//default quarter 2/2023
        arrange();
        
        Quarter prev_quarter = new Quarter(1,2023);
       
        assertEquals(prev_quarter, quarter.previous());
    }
    
    @Test
    public void testPrevious1() {
    	
        arrange(1,2023);
        
        Quarter prev_quarter = new Quarter(4,2022);
        
        assertEquals(prev_quarter, quarter.previous());
    }
    
    @Test
    public void testPrevious2() {
    	
        arrange(1,1900);
        
        assertEquals(null, quarter.previous());
    }
    
    @Test
    public void testPrevious3() {
    	
        arrange(7,2021);
        
        Quarter prev_quarter = new Quarter(3,2021);
        
        assertEquals(prev_quarter, quarter.previous());
    }
  
    @Test
    public void testNext() {
    	
    	//default quarter 2/2023
        arrange();
        
        Quarter next_quarter = new Quarter(3,2023);
       
        assertEquals(next_quarter, quarter.next());
    }
    
    @Test
    public void testNext1() {
    	
        arrange(4,9999);
       
        assertEquals(null, quarter.next());
    }
    @Test
    public void testNext2() {
    	
    	//out of range
        arrange(-3,2027);
       
        //get Q-2/2027
        assertEquals(null, quarter.next());
    }
    
    @Test
    public void testToString()
    {
    	arrange();
    	
    	assertEquals("Q2/2023", quarter.toString());
    	
    }
    
    @Test
    public void testToString1()
    {
    	arrange(4,2050);
    	
    	assertEquals("Q4/2050", quarter.toString());
    	
    }
    
    
    @Test(expected=IllegalArgumentException.class)
    public void testToString2()
    {
    	//quad out of range
    	arrange(7,10000);
    	
    	assertEquals("Q7/10000", quarter.toString());
    	
    }
    
    @Test
    public void testHashCode()
    {
    	//default
    	arrange();
    	
    	//default
    	Date date = new Date();
    	Quarter q = new Quarter(date);
    	
    	assertEquals(q.hashCode(), quarter.hashCode());
    }
    
    @Test
    public void testGetSerialIndex1(){
    	
        arrange();
        
        int actualSerialIndex = (quarter.getYear().getYear() *4 )+ quarter.getQuarter();
   
        assertEquals(actualSerialIndex, quarter.getSerialIndex());
        
    }
}
