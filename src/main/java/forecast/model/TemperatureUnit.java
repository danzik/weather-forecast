package forecast.model;

public enum TemperatureUnit {
    CELSIUS {
        public double toCelsius(double temperature)   { return temperature; }
        public double toFahreneit(double temperature)   { return ((temperature * 9 / 5) + 32); }
        public double convert(double temperature, TemperatureUnit u) { return u.toCelsius(temperature); }
    },

    FAHRENHEIT {
        public double toCelsius(double temperature)   { return ((temperature - 32)) * 5 / 9; }
        public double toFahreneit(double temperature)  { return temperature; }
        public double convert(double temperature, TemperatureUnit u) { return u.toFahreneit(temperature); }
    };

    public double toCelsius(double temperature) {
        throw new AbstractMethodError();
    }

    public double toFahreneit(double temperature) {
        throw new AbstractMethodError();
    }

    public double convert(double temperature, TemperatureUnit sourceUnit) {
        throw new AbstractMethodError();
    }

}