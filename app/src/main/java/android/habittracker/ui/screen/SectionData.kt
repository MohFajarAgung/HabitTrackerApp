package android.habittracker.ui.screen

data class SectionData(
    val registration : String = "registraion",
    val dashboard : DashBoardScreenData = DashBoardScreenData(),
)

data class DashBoardScreenData(
    val homeScreen : String = "homeScreen",
    val detailHabit : String = "detailHabitScreen"
)