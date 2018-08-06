package io.jcervelin.broker.message.templates;

import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import io.jcervelin.broker.message.domains.Sale;

import static br.com.six2six.fixturefactory.Fixture.of;


public class SaleTemplate implements TemplateLoader {
    public static final String APPLE_20 = "apple 20";
    public static final String ORANGE_65 = "orange 65";

    @Override
    public void load() {
        of(Sale.class).addTemplate(APPLE_20, new Rule() {{
            add("product", "apple");
            add("price", 20d);
        }});

        of(Sale.class).addTemplate(ORANGE_65, new Rule() {{
            add("product", "orange");
            add("price", 65d);
        }});


//        of(Criticism.class).addTemplate("categoryCriticism_freedom_family_invalid_fixed_date").inherits("categoryCriticism_freedom_family_invalid", new Rule() {{
//            add("versionDate", LocalDateTime.of(2018, 4, 10, 10, 30, 30));
//        }});


    }
}
