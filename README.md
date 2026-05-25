# Calculadora y Convertidor - Java Swing

Aplicación de escritorio desarrollada en Java con interfaz gráfica (Swing) que permite realizar operaciones matemáticas y conversiones de temperatura y moneda.

## Funcionalidades

### Operaciones Matemáticas
- Suma
- Resta
- Multiplicación
- División (con validación de división por cero)

### Conversión de Temperatura
- Celsius a Fahrenheit
- Fahrenheit a Celsius

### Conversión de Moneda
- Dólares (USD) a Pesos Colombianos (COP)
- Pesos Colombianos (COP) a Dólares (USD)
- Tasa fija: **$3.800 COP por 1 USD**

## Requisitos

- Java JDK 8 o superior

## Compilar y ejecutar

```bash
# Compilar
javac CalculadoraApp.java

# Ejecutar
java CalculadoraApp
```

## Tecnologías

- Java SE
- Java Swing (`JFrame`, `JLabel`, `JTextField`, `JButton`, `JOptionPane`, `ActionListener`)

## Validaciones

- No permite campos vacíos
- Valida que los datos ingresados sean numéricos
- Evita la división por cero
- Mensajes de error descriptivos mediante `JOptionPane`
