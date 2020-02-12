package hotgammon.domain;
import org.junit.*;
import static org.junit.Assert.*;

import java.util.Iterator;
/** Testing of the location enumeration.
 
   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published by CRC Press.
   Author: 
     Henrik B Christensen 
     Department of Computer Science
     Aarhus University
   
   Please visit http://www.baerbak.com/ for further information.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/
public class TestLocation {

  @Test
  public void shouldHaveProperNames() {
    // not a full test
    assertEquals( "B1", Location.B1.toString() );
    assertEquals( "B5", Location.B5.toString() );
    assertEquals( "R12", Location.R12.toString() );
    assertEquals( "R4", Location.R4.toString() );
    assertEquals( "B_BEAR_OFF", Location.B_BEAR_OFF.toString() );
    assertEquals( "R_BEAR_OFF", Location.R_BEAR_OFF.toString() );
    assertEquals( "R_BAR", Location.R_BAR.toString() );
    assertEquals( "B_BAR", Location.B_BAR.toString() );
  }

  @Test public void shouldCalculateDistanceCorrectly() {
    assertEquals( 1, Location.distance( Location.B2, Location.B1 ) );
    assertEquals( -1, Location.distance( Location.B1, Location.B2 ) );

    assertEquals( -1, Location.distance( Location.R2, Location.R1 ) );
    assertEquals( 1, Location.distance( Location.R1, Location.R2 ) );


    assertEquals( 6, Location.distance( Location.B8, Location.B2 ) );

    // moving from red to black's tables and opposite
    assertEquals( 3, Location.distance( Location.R10, Location.B12 ) );
    assertEquals( -5, Location.distance( Location.B11, Location.R9 ) );
  }
  
  @Test public void shouldCalculateBarDistanceCorrectly() {
    // the distance between the black bar and B3 is -3 as
    // die value 3 thrown by red shold allow red to move a checker
    // from the bar to B3
    assertEquals( -3, Location.distance( Location.R_BAR, Location.B3 ) );

    assertEquals( 4, Location.distance( Location.B_BAR, Location.R4 ) );
    
  }

  @Test public void shouldCalculateBearOffDistanceCorrectly() {
    assertEquals( 4, Location.distance( Location.B4, Location.B_BEAR_OFF ) );
    assertEquals( -6, Location.distance( Location.R6, Location.R_BEAR_OFF ) );
  }

  @Test public void shouldFindProperLocationBasedOnDistance() {
    // what location is the to location when red player has a die
    // 4 and a checker on B2: B6
    assertEquals( Location.B6, 
                  Location.findLocation( Color.RED, Location.B2, 4) );

    assertEquals( Location.B2, 
                  Location.findLocation( Color.BLACK, Location.B6, 4) );

    // moving off the board
    assertEquals( Location.B_BEAR_OFF, 
                  Location.findLocation( Color.BLACK, Location.B2, 4) );
    assertEquals( Location.B_BEAR_OFF, 
                  Location.findLocation( Color.BLACK, Location.B2, 6) );
    assertEquals( Location.R_BEAR_OFF, 
                  Location.findLocation( Color.RED, Location.R2, 6) );

    // moving in from the bar
    assertEquals( Location.R3, 
                  Location.findLocation( Color.BLACK, 
                                         Location.B_BAR, 3) );
    assertEquals( Location.B6, 
                  Location.findLocation( Color.RED, 
                                         Location.R_BAR, 6) );
    // passing from red to black table and opposite
    assertEquals( Location.B10, 
                  Location.findLocation( Color.BLACK, 
                                         Location.R10, 5) );
    assertEquals( Location.R10, 
                  Location.findLocation( Color.RED, 
                                         Location.B10, 5) );
  }

  @Test public void shouldIterateThrough28Locations() {
    int count = 0;
    for( Location l : Location.values() ) {
      count++; 
      assertTrue( l != null );
    }
    assertEquals( 28, count );
  }
  
}
