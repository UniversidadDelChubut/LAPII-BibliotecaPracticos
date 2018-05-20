
int sensorConsumoPin = A0;    // select the input pin for the potentiometer
int sensorBombaPin = A1;
int ledTiempo = 10;      // select the pin for the LED
int sensorConsumoValue = 0; 
int sensorBombaValue = 0; 

int loops = 0;

double flujoEntrada = 200;
double flujoSalida = 150;
double capacidadTotal = 500;
double contenido = 250;
int porcentajePotenciaBomba = 100;



void setup() {
  
  // put your setup code here, to run once:
  Serial.begin(9600);
  pinMode(ledTiempo, OUTPUT);
  pinMode(11, OUTPUT);
  digitalWrite(11, HIGH);

}


void loop() {

 sensorConsumoValue = analogRead(sensorConsumoPin);
 sensorBombaValue = analogRead(sensorBombaPin);

 double porcentajeAumentoDemanda = ((double)sensorConsumoValue / 100);
 porcentajePotenciaBomba = (int)((double)sensorBombaValue / 10);

 double egresoMinuto = (flujoSalida * (1 + porcentajeAumentoDemanda / 100)) / 60;
  
 double ingresoMinuto = (flujoEntrada * (double)porcentajePotenciaBomba 
  /(double)100)/ (double)60;

 contenido = contenido + ingresoMinuto;
 if (contenido > capacidadTotal) {
   contenido = capacidadTotal;
 }
 
 contenido = contenido - egresoMinuto;
 if (contenido <  0) {
   contenido = 0;
 }
  
  
  // put your main code here, to run repeatedly:
  digitalWrite(ledTiempo, HIGH);   // turn the LED on (HIGH is the voltage level)
  delay(1000);                       // wait for a second
  digitalWrite(ledTiempo, LOW);    // turn the LED off by making the voltage LOW
  delay(1000);                       // wait for a second
  loops++;
  Serial.print(porcentajePotenciaBomba);
  Serial.print(" ");
  Serial.print(ingresoMinuto);
  Serial.print(" ");
  Serial.print(porcentajeAumentoDemanda);
  Serial.print(" ");
  Serial.print(egresoMinuto);
  Serial.print(" ");
  Serial.print(contenido);
  Serial.print("\n");
    
}
