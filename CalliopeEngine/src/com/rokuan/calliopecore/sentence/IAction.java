package com.rokuan.calliopecore.sentence;

/**
 * Created by LEBEAU Christophe on 19/02/2015.
 */
public interface IAction extends IValue, IFieldObject, IStateObject {
    enum ActionType {
        ABANDON,
        ABSTRACT,
        ACCEPT,
        ACCESS,
        ACHIEVE,
        ACT,
        ACTIVATE,
        ADAPT,
        ADD,
        ADDRESS,
        AFFIRM,
        AGREE,
        AIM,
        ALERT,
        ALIGN,
        ALLOW,
        ANALYZE,
        ANIMATE,
        ANNOUNCE,
        ANSWER,
        APPEAR_AGAIN,
        APPLY,
        APPROACH,
        ARRANGE,
        ASK,
        ASSEMBLE,
        ASSIST,
        ATTACH,
        ATTACH_LINK,
        ATTACK,
        AVOID,
        BE,
        BE_NAMED,
        BEAT,
        BECOME,
        BELONG,
        BEND,
        BLEED,
        BLOCK,
        BLOW,
        BORN_AGAIN,
        BREAK,
        BREAK_DESTROY,
        BREATHE,
        BRING,
        BROWSE,
        BUILD_AGAIN,
        BURN,
        BUY,
        CALL,
        CALL_AGAIN,
        CALM,
        CAN,
        CANCEL,
        CARRY,
        CATCH,
        CHANGE,
        CHANGE_PATH,
        CHEAT,
        CHECK,
        CHOOSE,
        CHOOSE_AGAIN,
        CLAP,
        CLEAN,
        CLEAR,
        CLICK,
        CLIMB,
        CLONE,
        CLOSE,
        CLOSE_AGAIN,
        CLOSE_INSIDE,
        COLOR,
        COLOR_RED,
        COME,
        COME_AFTER,
        COME_AGAIN,
        COME_BEFORE,
        COMMENT,
        COMPARE,
        COMPLETE,
        COMPOSE,
        COMPUTE,
        COMPUTE_EVALUATE,
        CONFIGURE,
        CONFIGURE_SETTINGS,
        CONFIRM,
        CONNECT,
        CONSUME,
        CONTACT,
        CONTACT_AGAIN,
        CONTINUE,
        CONTROL,
        CONVERT,
        COPY,
        COPY_BEHAVIOR,
        CORRECT,
        COUNT,
        COUNTER,
        COVER,
        CREATE_AGAIN,
        CROSS,
        CRY,
        CURE,
        CUT,
        DANCE,
        DECLARE,
        DECLINE,
        DECREASE,
        DECREASE_AGAIN,
        DECREASE_AGE,
        DECREASE_DIFFICULTY,
        DECREASE_DISTANCE,
        DECREASE_HARDNESS,
        DECREASE_HEIGHT,
        DECREASE_IMPORTANCE,
        DECREASE_LIGHT,
        DECREASE_MONEY,
        DECREASE_POWER,
        DECREASE_SIZE,
        DECREASE_SPEED,
        DECREASE_STRENGTH,
        DECREASE_TEMPERATURE,
        DECREASE_WEIGHT,
        DEFEND,
        DEFINE,
        DELEGATE,
        DELETE,
        DEMONSTRATE,
        DENY,
        DESCRIBE,
        DETACH,
        DETECT,
        DEVELOP,
        DISCOVER,
        DISPLAY,
        DISPLAY_SHOW,
        DIVIDE,
        DO,
        DOWNLOAD,
        DO_AGAIN,
        DRAW,
        DREAM,
        DRESS,
        DRINK,
        DRIVE,
        DRY,
        EAT,
        EAT_NIGHT,
        EAT_NOON,
        EDIT,
        END,
        ENTER,
        EQUIP,
        ESCAPE,
        EVADE,
        EXECUTE,
        EXIST,
        EXIT,
        EXIT_AGAIN,
        EXPLAIN,
        EXPLORE,
        EXTRACT,
        FAIL,
        FALL,
        FALL_AGAIN,
        FEED,
        FEEL,
        FIGHT,
        FILL,
        FIND,
        FIRE,
        FIX,
        FLY,
        FOLLOW,
        FOLLOW_TRACE,
        FORBID,
        FORCE,
        FORGET,
        FORGIVE,
        FORMAT,
        FREEZE,
        GATHER,
        GENERATE,
        GET,
        GIVE,
        GIVE_AGAIN,
        GIVE_BACK,
        GIVE_GIFT,
        GIVE_MONEY,
        GIVE_MONEY_BACK,
        GO,
        GROUP,
        GROW,
        GROW_AGAIN,
        GROW_EVOLVE,
        GUARANTEE,
        HATE,
        HAVE,
        HAVE_AGAIN,
        HEAR,
        HELP,
        HESITATE,
        HIDE,
        HIT,
        HIT_HAMMER,
        HOLD,
        HOPE,
        HUNT,
        HURRY,
        HURT,
        IDENTIFY,
        IDENTIFY_POINT,
        IGNORE,
        IMPORT,
        IMPRESS,
        INCREASE,
        INCREASE_AGAIN,
        INCREASE_BEAUTY,
        INCREASE_DEPTH,
        INCREASE_DIFFICULTY,
        INCREASE_DISTANCE,
        INCREASE_HEIGHT,
        INCREASE_LIGHT,
        INCREASE_MONEY,
        INCREASE_POWER,
        INCREASE_PRICE,
        INCREASE_PRICE_AGAIN,
        INCREASE_SIZE,
        INCREASE_SPEED,
        INCREASE_STRENGTH,
        INCREASE_TEMPERATURE,
        INCREASE_WIDTH,
        INFORM,
        INHERIT,
        INSERT,
        INSERT_PERSON,
        INSTALL,
        INVITE,
        IRON,
        JOKE,
        JUMP,
        KEEP,
        KILL,
        KNOW,
        LAST,
        LEAD,
        LEARN,
        LEARN_AGAIN,
        LEARN_INFORM,
        LEAVE,
        LET,
        LET_GO,
        LICK,
        LIE,
        LIFT,
        LIKE,
        LIMIT,
        LINK,
        LIST,
        LISTEN,
        LIVE,
        LOAD,
        LOCK,
        LOOK,
        LOOK_AT,
        LOOK_LIKE,
        LOOK_SPY,
        LOSE,
        LOVE,
        LOWER,
        MAIL,
        MAKE,
        MAKE_AGAIN,
        MANAGE,
        MEASURE,
        MEASURE_METER,
        MEET,
        MISS,
        MODIFY_APPEARANCE,
        MOVE,
        MOVE_BACK,
        MOVE_IN,
        MULTIPLY,
        MULTIPLY_2,
        MUST,
        NAME,
        NAME_AGAIN,
        NEED,
        NOTE,
        NOTICE,
        NOTIFY,
        NULLIFY_SOUND,
        OCCUPY,
        OPEN,
        OPEN_AGAIN,
        ORDER,
        OSE,
        OWE,
        PAINT_AGAIN,
        PARK,
        PASS,
        PASS_AGAIN,
        PASTE,
        PAUSE,
        PAY_MONEY,
        PICK,
        PLACE,
        PLAY,
        PLAY_AGAIN,
        POINT,
        POSITION,
        POST,
        PRAY,
        PREPARE,
        PRESS,
        PRINT,
        PROGRESS,
        PROPOSE,
        PROTECT,
        PROVOKE,
        PULL,
        PUSH,
        PUT,
        PUT_IN,
        QUIT,
        QUOTE,
        RAIN,
        READ,
        READ_AGAIN,
        REALISE,
        RECEIVE,
        RECORD,
        RECORD_VIDEO,
        RECRUIT,
        REFER,
        REFERENCE,
        REFRESH,
        REMEMBER,
        REMOVE,
        RENT,
        REPAIR,
        REPLACE,
        REPRESENT,
        RESIST,
        RESPECT,
        RESTORE,
        RESUME,
        RESURRECT,
        RING,
        ROLL,
        RUN,
        SATISFY,
        SAVE,
        SAY,
        SAY_LOUD,
        SAY_PRONOUNCE,
        SEARCH,
        SEE,
        SEEM,
        SEE_AGAIN,
        SELECT,
        SELL,
        SEND,
        SEND_AGAIN,
        SEND_BACK,
        SET,
        SET_ORIENTATION,
        SHAKE,
        SHARE,
        SHINE,
        SHOT,
        SHOT_AGAIN,
        SHOUT,
        SHOW,
        SHUT,
        SIGN,
        SING,
        SIT,
        SIT_AGAIN,
        SLAP,
        SLEEP,
        SLEEP_AGAIN,
        SMILE,
        SORT,
        SPECIFY,
        SPELL,
        SPY,
        START,
        START_AGAIN,
        STAY,
        STEAL,
        STOP,
        STORE,
        STUDY,
        STUN,
        SUBSCRIBE,
        SUBSCRIBE_AGAIN,
        SUBSTITUTE,
        SUBTRACT,
        SUCCEED,
        SUCK,
        SUGGEST,
        SUPPORT,
        SUPPOSE,
        SURF,
        SWIM,
        TAKE,
        TAKE_AGAIN,
        TAKE_MONEY,
        TAKE_OUT,
        TAKE_PICTURE,
        TALK,
        TALK_ABOUT,
        TALK_AGAIN,
        TALK_SPEAK,
        TEACH,
        TELL,
        TEST,
        THANK,
        THERE_IS,
        THINK,
        THINK_ABOUT,
        THINK_IMAGINE,
        THROW,
        THROW_AGAIN,
        TODO_1,
        TOUCH,
        TRADE,
        TRANSFER,
        TRANSLATE,
        TRAP,
        TROW_AGAIN,
        TRU,
        TRY_AGAIN,
        TURN,
        TURN_OFF,
        TURN_ON,
        UNDEFINED,
        UNDERLINE,
        UNDERSTAND,
        UNDRESS,
        UNLEARN,
        UNLINK,
        UNLOCK,
        USE,
        USE_MONEY,
        VISIT,
        WAIT,
        WAKE_UP,
        WALK,
        WANT,
        WANT_AGAIN,
        WANT_STRONGLY,
        WASH,
        WAVE,
        WEAR,
        WEIGHT,
        WELCOME,
        WHIP,
        WIN,
        WISH,
        WORK,
        WRITE,
        WRITE_AGAIN
    }

    enum Tense {
        PAST,
        PRESENT,
        FUTURE
    }

    enum Form {
        INDICATIVE,
        CONDITIONAL,
        SUBJUNCTIVE,
        IMPERATIVE,
        INFINITIVE,
        PARTICIPLE,
    }

    ActionType getAction();

    Form getForm();

    Tense getTense();

    boolean isTargetAction();
}