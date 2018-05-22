#define FALSE 0
#define TRUE 1

#define LED_TIEMPO  13
#define LED_BOMBA   10
#define LED_REBALSA 11


int sensorConsumoPin = A0;    // select the input pin for the potentiometer
int sensorBombaPin  = A1;

int loops = 0;

double flujoEntrada = 200;
double flujoSalida = 150;
double capacidadTotal = 50;
double contenido = 25;
int porcentajePotenciaBomba = 100;
int rebalsa = FALSE;
int bombaActivada = TRUE;
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
    imprimirSalida();
}

void leerEntrada() {
  if (Serial.available() > 0) {
    int rByte = Serial.read();
    //if (rByte & 0x01 == 0) {
      Serial.write(1);  
    //} else {
    //  Serial.write(2);
    //}
    Serial.flush();
  }
}


void imprimirSalida() {
    /*
    Serial.print(porcentajePotenciaBomba);
    Serial.print(" ");
    Serial.print(ingresoMinuto);
    Serial.print(" ");
    Serial.print(egresoMinuto);
    Serial.print(" ");
    Serial.print(contenido);
    Serial.print("\n");
    */
  
}

