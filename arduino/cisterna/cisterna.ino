#include <stdio.h>

#define FALSE 0
#define TRUE 1

#define ERROR_FORMATO 1
#define ERROR_PARIDAD 2


#define LED_TIEMPO  13
#define LED_BOMBA   10
#define LED_REBALSA 11

byte buffer [7];
int pos = 0;

int sensorConsumoPin = A0;    // select the input pin for the potentiometer
int sensorBombaPin  = A1;

int loops = 0;

double flujoEntrada = 40;
double flujoSalida = 30;
double capacidadTotal = 50;
double contenido = 25;
int porcentajePotenciaBomba = 100;
int rebalsa = FALSE;
int bombaActivada = TRUE;
int salidaActivada = TRUE;
int controlExternoActivado = FALSE;


double egresoMinuto = 0;
double ingresoMinuto = 0;


void setup() {
    // put your setup code here, to run once:
    Serial.begin(9600);
    pinMode(LED_TIEMPO, OUTPUT);
    pinMode(LED_BOMBA, OUTPUT);
    pinMode(LED_REBALSA, OUTPUT);
    
}


void loop() {

    leerEntrada();

    int sensorConsumoValue = analogRead(sensorConsumoPin);
    int sensorBombaValue = analogRead(sensorBombaPin);

    double porcentajeAumentoDemanda = ((double)sensorConsumoValue / 100);
    egresoMinuto = (flujoSalida * (1 + porcentajeAumentoDemanda / 100)) / 60;


    if (controlExternoActivado == FALSE) {
      porcentajePotenciaBomba = (int)((double)sensorBombaValue / 10);
    } else {
      porcentajePotenciaBomba = 100;
    }

    ingresoMinuto = (flujoEntrada * (double)porcentajePotenciaBomba /(double)100)/ (double)60;

    if(bombaActivada == TRUE) { 
      contenido = contenido + ingresoMinuto;
    }

    
    rebalsa = FALSE;
    
    if (contenido > capacidadTotal) {
      contenido = capacidadTotal;
      rebalsa = TRUE;

      if (controlExternoActivado == FALSE) {
          bombaActivada = FALSE;
      }
    }
    
    contenido = contenido - egresoMinuto;
    if (contenido <  0) {
    contenido = 0;
    }




    if (rebalsa == TRUE)
        digitalWrite(LED_REBALSA, HIGH);
    else
        digitalWrite(LED_REBALSA, LOW);

    if (bombaActivada == TRUE)
        digitalWrite(LED_BOMBA, HIGH);
    else
        digitalWrite(LED_BOMBA, LOW);

        
    // put your main code here, to run repeatedly:
    digitalWrite(LED_TIEMPO, LOW);    // turn the LED off by making the voltage LOW
    delay(900);                       // wait for a second
    digitalWrite(LED_TIEMPO, HIGH);   // turn the LED on (HIGH is the voltage level)
    delay(100);                       // wait for a second
    loops++;
    informarSalida();
}

void leerEntrada() {
    int finTrama = FALSE;
    
    while (Serial.available() > 0) {

        //Serial.write('\n');
        //Serial.write(pos);
    
        if (pos > 6) {
          pos = 0;
          return;
        }
        
        
        buffer [pos] = Serial.read();
        /**Verificar la cabecera*/
        //Serial.write(buffer [pos]);
        
        if (
            (pos == 0 && (buffer[pos] != 0xFF) ) ||
            (pos == 1 && (buffer[pos] != 0xAA) ) ||
            (pos == 2 && (buffer[pos] != 0x00) )            ) 
        {
            informarError(ERROR_FORMATO);
            pos = 0;
            return;
        }
        
        
        if(pos == 6) {
          int tipoMensaje = (buffer[3] >> 3) & 0x1F;

          int paridad = 0;
          for (int i = 0; i < 7; i++) {
            for (int b = 0 ; b < 8;  b++ ) {
              paridad += (buffer[i] >> b) & 1;
            }
          }
          
          if (paridad % 2 != 0) {
            informarError(ERROR_PARIDAD);
            pos = 0;
            return;
          }

          /*
          Serial.print("paridad :");
          Serial.print(paridad);
          Serial.print("\n");
          
          Serial.print("tipoMensaje:");
          Serial.print(tipoMensaje);
          Serial.print("\n");
          Serial.flush();
          */
          if (tipoMensaje == 1) {
            parsearMensajeTipo_1();
          }
          if (tipoMensaje == 2) {
            parsearMensajeTipo_2();
          }

          Serial.flush();
        }
        pos++;
        
    }
    //Serial.write('O');

}

void parsearMensajeTipo_1() {
  
    int capacidad  = ((buffer[3] & 0x03) << 8) | buffer[4] ;
    int entrada = buffer[5]  ;
    int salida  = buffer[6] ;


    flujoEntrada = entrada;
    flujoSalida = salida;
    capacidadTotal = capacidad;
    contenido = capacidad / 2;

    Serial.print("RCV OK MSGTYPE:1");
    Serial.print(" capacidad:");
    Serial.print(capacidad);
    Serial.print(" entrada:");
    Serial.print(entrada);  
    Serial.print(" salida:");
    Serial.print(salida);  
    Serial.print("\n");    
}


void parsearMensajeTipo_2() {
  
    int potencia = ((buffer[3] & 0x03) << 5) | ((buffer[4] >>3) & 0x1F) ;
    int salidaAbierta =  (buffer[4] & 0x04)  > 0;
    if (potencia > 100) {
      potencia = 100;
    }
    
    porcentajePotenciaBomba = potencia;
    salidaActivada = salidaAbierta;
    
    Serial.print("RCV OK MSGTYPE:2");
    Serial.print(" potencia:");
    Serial.print(potencia);
    Serial.print(" salida_abierta:");
    Serial.print(salidaAbierta ? 'S' : 'N');
    Serial.print("\n");
}

void parsearMensajeTipo_3() {
    Serial.print("RCV OK MSGTYPE:2");
    Serial.print("\n");

    informarSalida();    
    
}

void informarSalida() {
    Serial.print("STATUS ");
    Serial.print(" potenciaBomba:");
    Serial.print(porcentajePotenciaBomba);
    Serial.print(" ingreso:");
    Serial.print(ingresoMinuto);
    Serial.print(" egreso:");
    Serial.print(egresoMinuto);
    Serial.print(" contenido:");
    Serial.print(contenido);
    Serial.print(" flujoEntrada:");
    Serial.print(flujoEntrada);
    Serial.print(" flujoSalida:");
    Serial.print(flujoSalida);

    Serial.print(" salidaActivada:");
    Serial.print(salidaActivada ? 'S': 'N');
    Serial.print(" rebalsa:");
    Serial.print(rebalsa ? 'S': 'N');
    Serial.print(" controlExternoActivado :");
    Serial.print(controlExternoActivado  ? 'S': 'N');

    
    Serial.print("\n");

    
  
}

void informarError (int tipo) {
  
    char  cadena [3];
    
    if (tipo == ERROR_PARIDAD) {
      Serial.print("Parity error: ");
    } else {
      Serial.print("Bad message: ");
    }
    
    for (int i = 0; i <= pos ; i++) {
      cadena[0] = '\0';
      cadena[1] = '\0';
      cadena[2] = '\0';
      sprintf(cadena, "%X" , buffer[i]);
      Serial.print(' ');
      if (buffer[i] <= 0x0F)
        Serial.print('0');
      Serial.print(cadena);
    }
    Serial.print('\n');
}



