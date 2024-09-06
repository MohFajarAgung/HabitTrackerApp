package android.habittracker.ui.screen

data class SectionData(
    val registration : String = "registraion",
    val dashboard : DashBoardScreenData = DashBoardScreenData(),
    val profileAndPayment : ProfileAndPayment = ProfileAndPayment()
)

data class DashBoardScreenData(
    val homeScreen : String = "homeScreen",
    val allHabitsScreen : String = "allHabitsScreen",
    val detailHabit : String = "detailHabitScreen",
)

data class ProfileAndPayment(
    val profileScreen : String = "profileScreen",
)