/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.ActionJournalStatus;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.Actionargs;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.Actionjournal;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.Actions;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.Channelentities;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.Channels;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.Cronstate;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.CustomerStatus;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.Databasechangelog;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.Emailconfigurations;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.Entities;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.Entitygraphs;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.Entityinstanceedges;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.Entityinstances;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.Entitytravelermap;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.Execguardstate;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.FaqwadMessages;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.FaqwadQueueDefinitions;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.FaqwadStates;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.FaqwadTypes;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.Fetchrecords;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.Fields;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.Integrationconfigs;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.Journeys;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.Journeystates;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.Latestjourneys;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.Maps;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.Milestones;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.Organizations;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.Picklistitems;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.Proctriggers;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.Rules;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.Smilestones;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.Staticentitymapspecification;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.Staticentitymetadata;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.Tickschedules;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.TravelerEventStates;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.TravelerEventTypes;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.TravelerEvents;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.Travelerchannels;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.Travelerrulestate;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.Travelers;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.Travelerstate;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.Umtransactions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.jooq.Catalog;
import org.jooq.Sequence;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;

public class Public
extends SchemaImpl {
    private static final long serialVersionUID = -1643888782L;
    public static final Public PUBLIC = new Public();
    public final Actionargs ACTIONARGS = Actionargs.ACTIONARGS;
    public final Actionjournal ACTIONJOURNAL = Actionjournal.ACTIONJOURNAL;
    public final Actions ACTIONS = Actions.ACTIONS;
    public final Channelentities CHANNELENTITIES = Channelentities.CHANNELENTITIES;
    public final Channels CHANNELS = Channels.CHANNELS;
    public final Cronstate CRONSTATE = Cronstate.CRONSTATE;
    public final Emailconfigurations EMAILCONFIGURATIONS = Emailconfigurations.EMAILCONFIGURATIONS;
    public final Entities ENTITIES = Entities.ENTITIES;
    public final Entitygraphs ENTITYGRAPHS = Entitygraphs.ENTITYGRAPHS;
    public final Entityinstanceedges ENTITYINSTANCEEDGES = Entityinstanceedges.ENTITYINSTANCEEDGES;
    public final Entityinstances ENTITYINSTANCES = Entityinstances.ENTITYINSTANCES;
    public final Entitytravelermap ENTITYTRAVELERMAP = Entitytravelermap.ENTITYTRAVELERMAP;
    public final Execguardstate EXECGUARDSTATE = Execguardstate.EXECGUARDSTATE;
    public final Fetchrecords FETCHRECORDS = Fetchrecords.FETCHRECORDS;
    public final Fields FIELDS = Fields.FIELDS;
    public final Integrationconfigs INTEGRATIONCONFIGS = Integrationconfigs.INTEGRATIONCONFIGS;
    public final Journeystates JOURNEYSTATES = Journeystates.JOURNEYSTATES;
    public final Journeys JOURNEYS = Journeys.JOURNEYS;
    public final Latestjourneys LATESTJOURNEYS = Latestjourneys.LATESTJOURNEYS;
    public final Maps MAPS = Maps.MAPS;
    public final Milestones MILESTONES = Milestones.MILESTONES;
    public final Organizations ORGANIZATIONS = Organizations.ORGANIZATIONS;
    public final Picklistitems PICKLISTITEMS = Picklistitems.PICKLISTITEMS;
    public final Proctriggers PROCTRIGGERS = Proctriggers.PROCTRIGGERS;
    public final Rules RULES = Rules.RULES;
    public final Smilestones SMILESTONES = Smilestones.SMILESTONES;
    public final Staticentitymapspecification STATICENTITYMAPSPECIFICATION = Staticentitymapspecification.STATICENTITYMAPSPECIFICATION;
    public final Staticentitymetadata STATICENTITYMETADATA = Staticentitymetadata.STATICENTITYMETADATA;
    public final Tickschedules TICKSCHEDULES = Tickschedules.TICKSCHEDULES;
    public final Travelerchannels TRAVELERCHANNELS = Travelerchannels.TRAVELERCHANNELS;
    public final Travelerrulestate TRAVELERRULESTATE = Travelerrulestate.TRAVELERRULESTATE;
    public final Travelerstate TRAVELERSTATE = Travelerstate.TRAVELERSTATE;
    public final Travelers TRAVELERS = Travelers.TRAVELERS;
    public final Umtransactions UMTRANSACTIONS = Umtransactions.UMTRANSACTIONS;
    public final ActionJournalStatus ACTION_JOURNAL_STATUS = ActionJournalStatus.ACTION_JOURNAL_STATUS;
    public final CustomerStatus CUSTOMER_STATUS = CustomerStatus.CUSTOMER_STATUS;
    public final Databasechangelog DATABASECHANGELOG = Databasechangelog.DATABASECHANGELOG;
    public final FaqwadMessages FAQWAD_MESSAGES = FaqwadMessages.FAQWAD_MESSAGES;
    public final FaqwadQueueDefinitions FAQWAD_QUEUE_DEFINITIONS = FaqwadQueueDefinitions.FAQWAD_QUEUE_DEFINITIONS;
    public final FaqwadStates FAQWAD_STATES = FaqwadStates.FAQWAD_STATES;
    public final FaqwadTypes FAQWAD_TYPES = FaqwadTypes.FAQWAD_TYPES;
    public final TravelerEventStates TRAVELER_EVENT_STATES = TravelerEventStates.TRAVELER_EVENT_STATES;
    public final TravelerEventTypes TRAVELER_EVENT_TYPES = TravelerEventTypes.TRAVELER_EVENT_TYPES;
    public final TravelerEvents TRAVELER_EVENTS = TravelerEvents.TRAVELER_EVENTS;

    private Public() {
        super("public", null);
    }

    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Sequence<?>> getSequences() {
        ArrayList result = new ArrayList();
        result.addAll(this.getSequences0());
        return result;
    }

    private final List<Sequence<?>> getSequences0() {
        return Arrays.asList(Sequences.ACTIONARGS_ID_SEQ, Sequences.ACTIONJOURNAL_ID_SEQ, Sequences.ACTION_JOURNAL_STATUS_ACTION_JOURNAL_STATUS_ID_SEQ, Sequences.ACTIONS_ID_SEQ, Sequences.CHANNELENTITIES_ID_SEQ, Sequences.CHANNELS_GENERATED_ID_SEQ, Sequences.CRONSTATE_ID_SEQ, Sequences.CUSTOMERS_ID_SEQ, Sequences.CUSTOMER_STATUS_CUSTOMER_STATUS_ID_SEQ, Sequences.EMAILCONFIGURATIONS_ID_SEQ, Sequences.ENTITIES_GENERATED_ID_SEQ, Sequences.ENTITYGRAPHS_ID_SEQ, Sequences.ENTITYINSTANCEEDGES_ID_SEQ, Sequences.ENTITYINSTANCES_ID_SEQ, Sequences.ENTITYTRAVELERMAP_ID_SEQ, Sequences.EXECGUARDSTATE_ID_SEQ, Sequences.FAQWAD_MESSAGES_FAQWAD_MESSAGE_ID_SEQ, Sequences.FIELDS_GENERATED_ID_SEQ, Sequences.JOURNEYS_ID_SEQ, Sequences.JOURNEYSTATES_JOURNEY_STATE_ID_SEQ, Sequences.MAPS_ID_SEQ, Sequences.MILESTONES_ID_SEQ, Sequences.PICKLISTITEMS_ID_SEQ, Sequences.PROCTRIGGERS_ID_SEQ, Sequences.RULES_ID_SEQ, Sequences.SFCONFIGS_ID_SEQ, Sequences.SFFETCHRECORDS_ID_SEQ, Sequences.SMILESTONES_ID_SEQ, Sequences.STATICENTITYMAPSPECIFICATION_ID_SEQ, Sequences.STATICENTITYMETADATA_ID_SEQ, Sequences.STATUS_ID_SEQ, Sequences.TICKSCHEDULES_ID_SEQ, Sequences.TRAVELERCHANNELS_ID_SEQ, Sequences.TRAVELER_EVENTS_TRAVELER_EVENT_ID_SEQ, Sequences.TRAVELERRULESTATE_ID_SEQ, Sequences.UMTRANSACTIONS_ID_SEQ, Sequences.USERS_ID_SEQ);
    }

    @Override
    public final List<Table<?>> getTables() {
        ArrayList result = new ArrayList();
        result.addAll(this.getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.asList(Actionargs.ACTIONARGS, Actionjournal.ACTIONJOURNAL, Actions.ACTIONS, Channelentities.CHANNELENTITIES, Channels.CHANNELS, Cronstate.CRONSTATE, Emailconfigurations.EMAILCONFIGURATIONS, Entities.ENTITIES, Entitygraphs.ENTITYGRAPHS, Entityinstanceedges.ENTITYINSTANCEEDGES, Entityinstances.ENTITYINSTANCES, Entitytravelermap.ENTITYTRAVELERMAP, Execguardstate.EXECGUARDSTATE, Fetchrecords.FETCHRECORDS, Fields.FIELDS, Integrationconfigs.INTEGRATIONCONFIGS, Journeystates.JOURNEYSTATES, Journeys.JOURNEYS, Latestjourneys.LATESTJOURNEYS, Maps.MAPS, Milestones.MILESTONES, Organizations.ORGANIZATIONS, Picklistitems.PICKLISTITEMS, Proctriggers.PROCTRIGGERS, Rules.RULES, Smilestones.SMILESTONES, Staticentitymapspecification.STATICENTITYMAPSPECIFICATION, Staticentitymetadata.STATICENTITYMETADATA, Tickschedules.TICKSCHEDULES, Travelerchannels.TRAVELERCHANNELS, Travelerrulestate.TRAVELERRULESTATE, Travelerstate.TRAVELERSTATE, Travelers.TRAVELERS, Umtransactions.UMTRANSACTIONS, ActionJournalStatus.ACTION_JOURNAL_STATUS, CustomerStatus.CUSTOMER_STATUS, Databasechangelog.DATABASECHANGELOG, FaqwadMessages.FAQWAD_MESSAGES, FaqwadQueueDefinitions.FAQWAD_QUEUE_DEFINITIONS, FaqwadStates.FAQWAD_STATES, FaqwadTypes.FAQWAD_TYPES, TravelerEventStates.TRAVELER_EVENT_STATES, TravelerEventTypes.TRAVELER_EVENT_TYPES, TravelerEvents.TRAVELER_EVENTS);
    }
}

