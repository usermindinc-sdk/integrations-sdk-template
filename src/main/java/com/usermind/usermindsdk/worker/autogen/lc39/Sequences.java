/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39;

import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import org.jooq.DataType;
import org.jooq.Schema;
import org.jooq.Sequence;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.SequenceImpl;

public class Sequences {
    public static final Sequence<Long> ACTIONARGS_ID_SEQ = new SequenceImpl<Long>("ActionArgs_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false));
    public static final Sequence<Long> ACTIONJOURNAL_ID_SEQ = new SequenceImpl<Long>("ActionJournal_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false));
    public static final Sequence<Long> ACTION_JOURNAL_STATUS_ACTION_JOURNAL_STATUS_ID_SEQ = new SequenceImpl<Long>("action_journal_status_action_journal_status_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false));
    public static final Sequence<Long> ACTIONS_ID_SEQ = new SequenceImpl<Long>("Actions_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false));
    public static final Sequence<Long> CHANNELENTITIES_ID_SEQ = new SequenceImpl<Long>("ChannelEntities_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false));
    public static final Sequence<Long> CHANNELS_GENERATED_ID_SEQ = new SequenceImpl<Long>("Channels_generated_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false));
    public static final Sequence<Long> CRONSTATE_ID_SEQ = new SequenceImpl<Long>("CronState_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false));
    public static final Sequence<Long> CUSTOMERS_ID_SEQ = new SequenceImpl<Long>("Customers_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false));
    public static final Sequence<Long> CUSTOMER_STATUS_CUSTOMER_STATUS_ID_SEQ = new SequenceImpl<Long>("customer_status_customer_status_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false));
    public static final Sequence<Long> EMAILCONFIGURATIONS_ID_SEQ = new SequenceImpl<Long>("EmailConfigurations_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false));
    public static final Sequence<Long> ENTITIES_GENERATED_ID_SEQ = new SequenceImpl<Long>("Entities_generated_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false));
    public static final Sequence<Long> ENTITYGRAPHS_ID_SEQ = new SequenceImpl<Long>("EntityGraphs_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false));
    public static final Sequence<Long> ENTITYINSTANCEEDGES_ID_SEQ = new SequenceImpl<Long>("EntityInstanceEdges_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false));
    public static final Sequence<Long> ENTITYINSTANCES_ID_SEQ = new SequenceImpl<Long>("EntityInstances_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false));
    public static final Sequence<Long> ENTITYTRAVELERMAP_ID_SEQ = new SequenceImpl<Long>("EntityTravelerMap_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false));
    public static final Sequence<Long> EXECGUARDSTATE_ID_SEQ = new SequenceImpl<Long>("ExecGuardState_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false));
    public static final Sequence<Long> FAQWAD_MESSAGES_FAQWAD_MESSAGE_ID_SEQ = new SequenceImpl<Long>("faqwad_messages_faqwad_message_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false));
    public static final Sequence<Long> FIELDS_GENERATED_ID_SEQ = new SequenceImpl<Long>("Fields_generated_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false));
    public static final Sequence<Long> JOURNEYS_ID_SEQ = new SequenceImpl<Long>("Journeys_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false));
    public static final Sequence<Long> JOURNEYSTATES_JOURNEY_STATE_ID_SEQ = new SequenceImpl<Long>("JourneyStates_journey_state_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false));
    public static final Sequence<Long> MAPS_ID_SEQ = new SequenceImpl<Long>("Maps_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false));
    public static final Sequence<Long> MILESTONES_ID_SEQ = new SequenceImpl<Long>("Milestones_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false));
    public static final Sequence<Long> PICKLISTITEMS_ID_SEQ = new SequenceImpl<Long>("PickListItems_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false));
    public static final Sequence<Long> PROCTRIGGERS_ID_SEQ = new SequenceImpl<Long>("ProcTriggers_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false));
    public static final Sequence<Long> RULES_ID_SEQ = new SequenceImpl<Long>("Rules_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false));
    public static final Sequence<Long> SFCONFIGS_ID_SEQ = new SequenceImpl<Long>("SFConfigs_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false));
    public static final Sequence<Long> SFFETCHRECORDS_ID_SEQ = new SequenceImpl<Long>("SFFetchRecords_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false));
    public static final Sequence<Long> SMILESTONES_ID_SEQ = new SequenceImpl<Long>("Smilestones_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false));
    public static final Sequence<Long> STATICENTITYMAPSPECIFICATION_ID_SEQ = new SequenceImpl<Long>("StaticEntityMapSpecification_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false));
    public static final Sequence<Long> STATICENTITYMETADATA_ID_SEQ = new SequenceImpl<Long>("StaticEntityMetadata_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false));
    public static final Sequence<Long> STATUS_ID_SEQ = new SequenceImpl<Long>("Status_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false));
    public static final Sequence<Long> TICKSCHEDULES_ID_SEQ = new SequenceImpl<Long>("TickSchedules_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false));
    public static final Sequence<Long> TRAVELERCHANNELS_ID_SEQ = new SequenceImpl<Long>("TravelerChannels_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false));
    public static final Sequence<Long> TRAVELER_EVENTS_TRAVELER_EVENT_ID_SEQ = new SequenceImpl<Long>("traveler_events_traveler_event_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false));
    public static final Sequence<Long> TRAVELERRULESTATE_ID_SEQ = new SequenceImpl<Long>("TravelerRuleState_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false));
    public static final Sequence<Long> UMTRANSACTIONS_ID_SEQ = new SequenceImpl<Long>("UMTransactions_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false));
    public static final Sequence<Long> USERS_ID_SEQ = new SequenceImpl<Long>("Users_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false));
}

