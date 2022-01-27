package istasenko.practicaltask.eshop.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

public class MoneySerializer extends StdSerializer<BigDecimal> {

    @Value("${spring.mvc.locale}")
    private String lang;

    public MoneySerializer() {
        this(null);
    }

    public MoneySerializer(Class<BigDecimal> t) {
        super(t);
    }

    @Override
    public void serialize(BigDecimal bigDecimal, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {

        jsonGenerator.writeString(bigDecimal.toString().concat(Currency.getInstance(Locale.US).getSymbol()));

    }

}
