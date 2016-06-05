package an.an7x7.Model;

import android.os.Debug;
import android.util.Log;

/**
 * Created by NachoLR on 04/06/2016.
 */
public class LineChecker {


    private Square[][] allSquares;


    public boolean checkForLine(int row, int column, Square[][] allSQ) {

        this.allSquares = allSQ;

        int linesPerformed = 0;
        int colorCheck = allSquares[row][column].getColor();

        //preparamos los booleans necesarios.
        for (int r = 0; r < 7; r++)
            for (int c = 0; c < 7; c++){
                allSquares[r][c].erasable = false;
                allSquares[r][c].visited = false;
            }

        //Comprobar las cuatro direcciones (dos diagonales, horizontal y vertical)

        if (checkHorizontalLine(row,column,colorCheck) >= 4){ // si hay linea horizontal.
            for (int c = 0; c < 7; c++){ //recorremos la linea
                if (allSquares[row][c].visited)
                    allSQ[row][c].erasable = true; // marcamos los visitados para ser borrados.
            }
        linesPerformed++;
        }

        if (checkVerticalLine(row, column, colorCheck) >= 4){ // si hay linea vertical.
            for (int r = 0; r < 7; r++){ //recorremos la linea
                if (allSquares[r][column].visited)
                    allSQ[r][column].erasable = true; // marcamos los visitados para ser borrados.
            }
            linesPerformed++;
        }

        if (checkNegativeDiagonal(row, column, colorCheck) >= 4){ // si hay linea diagonal_negativa.

            int r = row;
            int c = column;
            while(r <= 6 && c >= 0){//recorremos la linea desde la posicion hacia abajo_izquierda
                if (allSquares[r][c].visited)
                    allSQ[r][c].erasable = true; // marcamos los visitados para ser borrados.
                r++;
                c--;
            }
            r = row;
            c = column;
            while( r >= 0 && c <= 6 ){ //recorremos la linea desde la posicion hacia arriba_derecha
                if (allSquares[r][c].visited)
                    allSQ[r][c].erasable = true; // marcamos los visitados para ser borrados.
                r--;
                c++;

            }
            linesPerformed++;
        }

        if (checkPositiveDiagonal(row, column, colorCheck) >= 4){ // si hay linea diagonal_positiva.

            int r = row;
            int c = column;
            while(r <= 6 && c <= 6){ //recorremos la linea desde la posicion hacia abajo_derecha
                if (allSquares[r][c].visited)
                    allSQ[r][c].erasable = true; // marcamos los visitados para ser borrados.
                r++;
                c++;
            }
            r = row;
            c = column;
            while(r >= 0 && c >= 0){ //recorremos la linea desde la posicion hacia arriba_izquierda
                if (allSquares[r][c].visited)
                    allSQ[r][c].erasable = true; // marcamos los visitados para ser borrados.
                r--;
                c--;
            }
            linesPerformed++;
        }


        //Revisamos si hay combo
        if(linesPerformed > 1){
            // COCOCOMBOOBREAKER!!!
            return true;
        }else if ( linesPerformed > 0){
            return true;
        }else{
            return false;
        }


    }

    private int checkHorizontalLine(int row, int column, int color) {

        if (allSquares[row][column].getColor() == color) {
            allSquares[row][column].visited = true; // me marco como visitado sólo si soy del color.
            if (column + 1 <= 6 && column-1 >= 0) {
                if (allSquares[row][column + 1].visited && !allSquares[row][column - 1].visited) { // si el de la derecha ya ha sido visitado y el de la izquierda no.
                    return (1 + checkHorizontalLine(row, column - 1, color)); // compruebo al de la izquierda.
                } else if (allSquares[row][column - 1].visited && !allSquares[row][column + 1].visited) { // si el de la izquierda ya ha sido visitado y el de la derecha no:
                    return (1 + checkHorizontalLine(row, column + 1, color)); // compruebo al de la derecha.
                } else {
                    return (1 + checkHorizontalLine(row, column + 1, color) + checkHorizontalLine(row, column - 1, color)); // si las dos anteriores dan falso, los dos vecinos están sin visitar.
                }
            }else if(column  == 6){ // CASO BASE de la recursividad, el siguiente cuadrado a la derecha no existe. No hay que hacer más llamadas hacia la derecha.
                if (!allSquares[row][column-1].visited){
                    return (1 + checkHorizontalLine(row, column - 1, color ));
                }else{
                    return 1;
                }
            }else if (column == 0){ // CASO BASE de la recursividad, el siguiente cuadrado a la izquierda no existe. No hay que hacer más llamadas hacia la izquierda.
                if (!allSquares[row][column+1].visited){
                    return (1 + checkHorizontalLine(row, column + 1, color ));
                }else{
                    return 1;
                }
            }else{
                return 1;
            }
        } else {
            return 0; // CASO BASE de la recursividad, el cuadrado es de otro color.
        }

    }

    private int checkVerticalLine(int row, int column, int color) {

        if (allSquares[row][column].getColor() == color) {
            allSquares[row][column].visited = true; // me marco como visitado sólo si soy del color.
            if (row + 1 <= 6 && row - 1 >= 0) {
                if (allSquares[row + 1 ][column].visited && !allSquares[row - 1 ][column].visited) { // si el de debajo ya ha sido visitado y el de arriba no.
                    return (1 + checkVerticalLine(row - 1, column, color)); // compruebo al de arriba.
                } else if (allSquares[row -1 ][column].visited && !allSquares[row + 1 ][column].visited) { // si el de arriba ya ha sido visitado y el de abajo no:
                    return (1 + checkVerticalLine(row + 1 , column, color)); // compruebo al de abajo.
                } else {
                    return (1 + checkVerticalLine(row + 1, column, color) + checkVerticalLine(row - 1, column , color)); // si las dos anteriores dan falso, los dos vecinos están sin visitar.
                }
            }else if(row  == 6){ // CASO BASE de la recursividad, el siguiente cuadrado inferior no existe. No hay que hacer más llamadas hacia abajo.
                if (!allSquares[row-1][column].visited){
                    return (1 + checkVerticalLine(row -1, column , color));
                }else{
                    return 1;
                }
            }else if (row == 0){ // CASO BASE de la recursividad, el siguiente cuadrado superior no existe. No hay que hacer más llamadas hacia arriba.
                if (!allSquares[row+1][column].visited){
                    return (1 + checkVerticalLine(row+1, column, color ));
                }else{
                    return 1;
                }
            }else{
                return 1;
            }
        } else {
            return 0; // CASO BASE de la recursividad, el cuadrado es de otro color.
        }

    }

    private int checkNegativeDiagonal(int row, int column, int color) {

        if (allSquares[row][column].getColor() == color) {
            allSquares[row][column].visited = true; // me marco como visitado sólo si soy del color.
            if (row + 1 <= 6 && row - 1 >= 0 && column + 1 <= 6 && column-1 >= 0) {
                if (allSquares[row + 1 ][column - 1 ].visited && !allSquares[row - 1 ][column + 1].visited) { // si el de debajo_izquierda ya ha sido visitado y el de arriba_derecha no.
                    return (1 + checkNegativeDiagonal(row - 1, column + 1, color)); // compruebo al de arriba_derecha.
                } else if (allSquares[row -1 ][column+1].visited && !allSquares[row + 1 ][column-1].visited) { // si el de arriba_derecha ya ha sido visitado y el de abajo_izquierda no:
                    return (1 + checkNegativeDiagonal(row + 1, column - 1, color)); // compruebo al de abajo_izquierda.
                } else {
                    return (1 + checkNegativeDiagonal(row + 1, column - 1, color) + checkNegativeDiagonal(row - 1, column + 1, color)); // si las dos anteriores dan falso, los dos vecinos están sin visitar.
                }
            }else if(row  == 6 && column != 6|| column == 0 && row != 0){ // CASO BASE de la recursividad, el siguiente cuadrado inferior_izquierda no existe. No hay que hacer más llamadas hacia abajo_izquierda.
                if (!allSquares[row-1][column+1].visited){
                    return (1 + checkNegativeDiagonal(row - 1, column + 1, color));
                } else {
                    return 1;
                }
            }else if (row == 0 && column != 0|| column == 6 && row != 6){ // CASO BASE de la recursividad, el siguiente cuadrado superior_derecha no existe. No hay que hacer más llamadas hacia arriba_derecha.
                if (!allSquares[row+1][column-1].visited){
                    return (1 + checkNegativeDiagonal(row + 1, column - 1, color ));
                }else{
                    return 1;
                }
            }else{
                return 1;
            }
        } else {
            return 0; // CASO BASE de la recursividad, el cuadrado es de otro color.
        }
    }

    private int checkPositiveDiagonal(int row, int column, int color) {

        if (allSquares[row][column].getColor() == color) {
            allSquares[row][column].visited = true; // me marco como visitado sólo si soy del color.
            if (row + 1 <= 6 && row - 1 >= 0 && column + 1 <= 6 && column-1 >= 0) {
                if (allSquares[row + 1 ][column + 1 ].visited && !allSquares[row - 1 ][column - 1].visited) { // si el de debajo_derecha ya ha sido visitado y el de arriba_izquierda no.
                    return (1 + checkPositiveDiagonal(row - 1, column -1, color)); // compruebo al de arriba_izquierda.
                } else if (allSquares[row -1 ][column-1].visited && !allSquares[row + 1 ][column+1].visited) { // si el de arriba_izquierda ya ha sido visitado y el de abajo_derecha no:
                    return (1 + checkPositiveDiagonal(row + 1, column + 1, color)); // compruebo al de abajo_derecha.
                } else {
                    return (1 + checkPositiveDiagonal(row + 1, column+1, color) + checkPositiveDiagonal(row - 1, column-1, color)); // si las dos anteriores dan falso, los dos vecinos están sin visitar.
                }
            }else if(row  == 6 && column != 0 || column == 6 && row != 0){ // CASO BASE de la recursividad, el siguiente cuadrado inferior_derecha no existe. No hay que hacer más llamadas hacia abajo_derecha.
                if (!allSquares[row-1][column-1].visited){
                    return (1 + checkPositiveDiagonal(row - 1, column - 1, color));
                }else{
                    return 1;
                }
            }else if (row == 0 && column != 6 || column == 0 && row != 6){ // CASO BASE de la recursividad, el siguiente cuadrado superior_izquierda no existe. No hay que hacer más llamadas hacia arriba_izquierda.
                if (!allSquares[row+1][column+1].visited){
                    return (1 + checkPositiveDiagonal(row+1, column+1, color ));
                }else{
                    return 1;
                }
            }else{
                return 1;
            }
        } else {
            return 0; // CASO BASE de la recursividad, el cuadrado es de otro color.
        }


    }



}
