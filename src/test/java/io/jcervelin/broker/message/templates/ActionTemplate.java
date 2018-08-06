package io.jcervelin.broker.message.templates;

import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import io.jcervelin.broker.message.domains.Action;
import io.jcervelin.broker.message.domains.Sale;

import static br.com.six2six.fixturefactory.Fixture.of;
import static io.jcervelin.broker.message.templates.SaleTemplate.APPLE_20;
import static io.jcervelin.broker.message.templates.SaleTemplate.ORANGE_65;

public class ActionTemplate implements TemplateLoader {

    public static final String ACTION_ADD = "action add 1 apple 20";
    public static final String ACTION_SUBTRACT = "subtract type 1 orange 65";
    public static final String ACTION_MULTIPLY = "multiply type 1 orange 65";

    @Override
    public void load() {
        of(Action.class).addTemplate(ACTION_ADD, new Rule() {{
            add("action", "add");
            add("sale", one(Sale.class, APPLE_20));
            add("amount", 3d);
        }});

        of(Action.class).addTemplate(ACTION_SUBTRACT, new Rule() {{
            add("action", "subtract");
            add("sale", one(Sale.class, ORANGE_65));
            add("amount", 4d);
        }});

        of(Action.class).addTemplate(ACTION_MULTIPLY, new Rule() {{
            add("action", "multiply");
            add("sale", one(Sale.class, ORANGE_65));
            add("amount", 2d);
        }});

    }
}