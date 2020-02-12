package hotgammon.domain;
/** This class represents an enumeration of colours used in
 * Backgammon.  We need to represent the red or the black colour - but
 * also in some casese neither of the colours, for instance if we ask
 * what colour the checkers have on an empty location on the board.
 * This is represented by the NONE colour.
 
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
public enum Color {

  RED(-1),
  NONE(0),
  BLACK(+1);
  
  // we represent color by an underlying numerical value,
  // thus negative value represent red, 0 no color, and
  // positive values represent black.
  private int sign;

  /** private constructor means that no one else but this scope is
   *  allowed to define instances. 
  */
  private Color(int sign) {
    this.sign = sign;
  }

  /** return the numerical value that this colour is defined by
   * @return the numerical value that this colour is defined by
   */
  public final int getSign() {
    return sign;
  }

  /** return the color that a numerical a value represents
   * @param value a numerical value that represents a color
   * @return the color the value represents
   */
  public static Color getColorFromNumerical(int value) {
    if (value < 0)
      return RED;
    if (value > 0)
      return BLACK;
    return NONE;
  }

  /** return the string representation of this color.
      @return string representation
  */
  public String toString() {
    switch (sign) {
      case -1 :
        return "RED";
      case +1 :
        return "BLACK";
      default :
        return "NONE";
    }
  }

}
