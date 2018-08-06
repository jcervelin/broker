package io.jcervelin.broker.message.templates;

import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import io.jcervelin.broker.message.domains.Action;
import io.jcervelin.broker.message.domains.Event;
import io.jcervelin.broker.message.domains.Sale;
import io.jcervelin.broker.message.usecases.Type;

import java.util.HashMap;
import java.util.Map;

import static br.com.six2six.fixturefactory.Fixture.of;
import static io.jcervelin.broker.message.templates.ActionTemplate.ACTION_ADD;
import static io.jcervelin.broker.message.templates.SaleTemplate.APPLE_20;
import static io.jcervelin.broker.message.templates.SaleTemplate.ORANGE_65;

public class EventTemplate implements TemplateLoader {

    public static final String EVENT_TYPE_1_APPLE_20 = "event type 1 apple 20";
    public static final String EVENT_TYPE_1_ORANGE_65 = "event type 1 orange 65";
    public static final String EVENT_TYPE_2_ORANGE_65 = "event type 2 orange 65";
    public static final String EVENT_TYPE_3_APPLE_20 = "event type 3 apple 20";

    @Override
    public void load() {
        of(Event.class).addTemplate(EVENT_TYPE_1_APPLE_20, new Rule() {{
            add("type", Type.TYPE_1.getType());
            add("content", one(Sale.class, APPLE_20));
        }});

        of(Event.class).addTemplate(EVENT_TYPE_1_ORANGE_65, new Rule() {{
            add("type", Type.TYPE_1.getType());
            add("content", one(Sale.class, ORANGE_65));
        }});

        of(Event.class).addTemplate(EVENT_TYPE_2_ORANGE_65, new Rule() {{
            add("type", Type.TYPE_2.getType());
            add("content", getOrangeMap());
        }});

        of(Event.class).addTemplate(EVENT_TYPE_3_APPLE_20, new Rule() {{
            add("type", Type.TYPE_3.getType());
            add("content", one(Action.class, ACTION_ADD));
        }});
    }

    private Map<Long, Sale> getOrangeMap() {
        Map<Long, Sale> map = new HashMap<>();
        Sale sale = new Sale("orange", 65d);
        map.put(3L, sale);
        return map;
    }


}
