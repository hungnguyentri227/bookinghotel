USE [master]
GO
/****** Object:  Database [BookingHotel]    Script Date: 3/28/2020 10:33:24 AM ******/
CREATE DATABASE [BookingHotel]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'BookingHotel', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\BookingHotel.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'BookingHotel_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\BookingHotel_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [BookingHotel] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [BookingHotel].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [BookingHotel] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [BookingHotel] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [BookingHotel] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [BookingHotel] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [BookingHotel] SET ARITHABORT OFF 
GO
ALTER DATABASE [BookingHotel] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [BookingHotel] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [BookingHotel] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [BookingHotel] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [BookingHotel] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [BookingHotel] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [BookingHotel] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [BookingHotel] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [BookingHotel] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [BookingHotel] SET  DISABLE_BROKER 
GO
ALTER DATABASE [BookingHotel] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [BookingHotel] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [BookingHotel] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [BookingHotel] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [BookingHotel] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [BookingHotel] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [BookingHotel] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [BookingHotel] SET RECOVERY FULL 
GO
ALTER DATABASE [BookingHotel] SET  MULTI_USER 
GO
ALTER DATABASE [BookingHotel] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [BookingHotel] SET DB_CHAINING OFF 
GO
ALTER DATABASE [BookingHotel] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [BookingHotel] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [BookingHotel] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'BookingHotel', N'ON'
GO
ALTER DATABASE [BookingHotel] SET QUERY_STORE = OFF
GO
USE [BookingHotel]
GO
/****** Object:  Table [dbo].[tbl_booking]    Script Date: 3/28/2020 10:33:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_booking](
	[bookid] [int] NOT NULL,
	[username] [nvarchar](50) NULL,
	[hotelname] [nvarchar](50) NOT NULL,
	[rooname] [nvarchar](50) NOT NULL,
	[categoryname] [nvarchar](50) NOT NULL,
	[totalprice] [float] NOT NULL,
	[datebooking] [date] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_category]    Script Date: 3/28/2020 10:33:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_category](
	[categoryid] [int] IDENTITY(1,1) NOT NULL,
	[categoryname] [nvarchar](50) NULL,
	[action] [bit] NULL,
 CONSTRAINT [PK_tbl_category] PRIMARY KEY CLUSTERED 
(
	[categoryid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_country]    Script Date: 3/28/2020 10:33:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_country](
	[countryid] [int] IDENTITY(1,1) NOT NULL,
	[countryname] [nvarchar](50) NULL,
 CONSTRAINT [PK_tbl_country] PRIMARY KEY CLUSTERED 
(
	[countryid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_hotel]    Script Date: 3/28/2020 10:33:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_hotel](
	[hotel_id] [int] IDENTITY(1,1) NOT NULL,
	[hotel_name] [nvarchar](50) NOT NULL,
	[address] [nvarchar](50) NOT NULL,
	[countryid] [int] NOT NULL,
	[action] [bit] NULL,
 CONSTRAINT [PK__tbl_hote__45FE7E26D450E49C] PRIMARY KEY CLUSTERED 
(
	[hotel_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_room]    Script Date: 3/28/2020 10:33:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_room](
	[room_id] [int] IDENTITY(1,1) NOT NULL,
	[room_name] [nvarchar](50) NOT NULL,
	[floor] [nvarchar](50) NOT NULL,
	[price] [int] NOT NULL,
	[hotel_id] [int] NULL,
	[category_id] [int] NULL,
	[isAction] [bit] NULL,
 CONSTRAINT [PK__tbl_room__19675A8A1C478A02] PRIMARY KEY CLUSTERED 
(
	[room_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_user]    Script Date: 3/28/2020 10:33:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_user](
	[userid] [int] IDENTITY(1,1) NOT NULL,
	[username] [nvarchar](50) NULL,
	[password] [nvarchar](50) NULL,
	[fullname] [nvarchar](50) NULL,
	[role] [nvarchar](50) NULL,
	[action] [bit] NULL,
 CONSTRAINT [PK_tbl_user] PRIMARY KEY CLUSTERED 
(
	[userid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[tbl_booking] ([bookid], [username], [hotelname], [rooname], [categoryname], [totalprice], [datebooking]) VALUES (427884545, N'1487489294752132', N'MuongThanh', N'A03', N'double', 100, CAST(N'2020-03-28' AS Date))
INSERT [dbo].[tbl_booking] ([bookid], [username], [hotelname], [rooname], [categoryname], [totalprice], [datebooking]) VALUES (1061401127, N'1487489294752132', N'MuongThanh', N'A04', N'family', 155, CAST(N'2020-03-28' AS Date))
INSERT [dbo].[tbl_booking] ([bookid], [username], [hotelname], [rooname], [categoryname], [totalprice], [datebooking]) VALUES (1525533756, N'trihung', N'MuongThanh', N'A06', N'family', 246, CAST(N'2020-03-28' AS Date))
INSERT [dbo].[tbl_booking] ([bookid], [username], [hotelname], [rooname], [categoryname], [totalprice], [datebooking]) VALUES (866372064, N'trihung', N'MuongThanh', N'A05', N'double', 222, CAST(N'2020-03-28' AS Date))
INSERT [dbo].[tbl_booking] ([bookid], [username], [hotelname], [rooname], [categoryname], [totalprice], [datebooking]) VALUES (1848532781, N'trihung', N'MuongThanh', N'A08', N'single', 110, CAST(N'2020-03-28' AS Date))
INSERT [dbo].[tbl_booking] ([bookid], [username], [hotelname], [rooname], [categoryname], [totalprice], [datebooking]) VALUES (2066371663, N'1487489294752132', N'MuongThanh', N'A01', N'single', 12, CAST(N'2020-03-28' AS Date))
INSERT [dbo].[tbl_booking] ([bookid], [username], [hotelname], [rooname], [categoryname], [totalprice], [datebooking]) VALUES (-35866461, N'trihung', N'MuongThanh', N'A01', N'single', 12, CAST(N'2020-03-26' AS Date))
INSERT [dbo].[tbl_booking] ([bookid], [username], [hotelname], [rooname], [categoryname], [totalprice], [datebooking]) VALUES (615259347, N'trihung', N'MuongThanh', N'A02', N'single', 14, CAST(N'2020-03-26' AS Date))
INSERT [dbo].[tbl_booking] ([bookid], [username], [hotelname], [rooname], [categoryname], [totalprice], [datebooking]) VALUES (1769138006, N'1487489294752132', N'MuongThanh', N'A07', N'single', 11, CAST(N'2020-03-28' AS Date))
INSERT [dbo].[tbl_booking] ([bookid], [username], [hotelname], [rooname], [categoryname], [totalprice], [datebooking]) VALUES (-451486086, N'1487489294752132', N'MuongThanh', N'B01', N'single', 13, CAST(N'2020-03-28' AS Date))
SET IDENTITY_INSERT [dbo].[tbl_category] ON 

INSERT [dbo].[tbl_category] ([categoryid], [categoryname], [action]) VALUES (1, N'single', 1)
INSERT [dbo].[tbl_category] ([categoryid], [categoryname], [action]) VALUES (2, N'double', 1)
INSERT [dbo].[tbl_category] ([categoryid], [categoryname], [action]) VALUES (3, N'family', 1)
SET IDENTITY_INSERT [dbo].[tbl_category] OFF
SET IDENTITY_INSERT [dbo].[tbl_country] ON 

INSERT [dbo].[tbl_country] ([countryid], [countryname]) VALUES (1, N'VN')
INSERT [dbo].[tbl_country] ([countryid], [countryname]) VALUES (2, N'USA')
INSERT [dbo].[tbl_country] ([countryid], [countryname]) VALUES (3, N'USS')
INSERT [dbo].[tbl_country] ([countryid], [countryname]) VALUES (4, N'China')
SET IDENTITY_INSERT [dbo].[tbl_country] OFF
SET IDENTITY_INSERT [dbo].[tbl_hotel] ON 

INSERT [dbo].[tbl_hotel] ([hotel_id], [hotel_name], [address], [countryid], [action]) VALUES (1, N'Diamond', N'88 Bill ', 2, 1)
INSERT [dbo].[tbl_hotel] ([hotel_id], [hotel_name], [address], [countryid], [action]) VALUES (3, N'MuongThanh', N'123 Nguyen Trai', 1, 1)
INSERT [dbo].[tbl_hotel] ([hotel_id], [hotel_name], [address], [countryid], [action]) VALUES (4, N'TuanAnh', N'11 Nguyen Hue', 1, 1)
INSERT [dbo].[tbl_hotel] ([hotel_id], [hotel_name], [address], [countryid], [action]) VALUES (5, N'LongAn', N'3A Tuan Tu', 1, 0)
INSERT [dbo].[tbl_hotel] ([hotel_id], [hotel_name], [address], [countryid], [action]) VALUES (6, N'NgheTinh', N'1 LeNin', 1, 0)
INSERT [dbo].[tbl_hotel] ([hotel_id], [hotel_name], [address], [countryid], [action]) VALUES (7, N'trihungdeptrai123', N'vinh123', 1, 0)
INSERT [dbo].[tbl_hotel] ([hotel_id], [hotel_name], [address], [countryid], [action]) VALUES (8, N'trihungxautrai', N'vinh', 4, 0)
INSERT [dbo].[tbl_hotel] ([hotel_id], [hotel_name], [address], [countryid], [action]) VALUES (9, N'hoadnt', N'hcm', 2, 0)
INSERT [dbo].[tbl_hotel] ([hotel_id], [hotel_name], [address], [countryid], [action]) VALUES (10, N'xovietnghetinh', N'vinh', 1, 0)
INSERT [dbo].[tbl_hotel] ([hotel_id], [hotel_name], [address], [countryid], [action]) VALUES (11, N'trihungdeptrai', N'vinh', 1, 0)
INSERT [dbo].[tbl_hotel] ([hotel_id], [hotel_name], [address], [countryid], [action]) VALUES (12, N'trihungdeptrai123a1', N'vinh', 1, 0)
INSERT [dbo].[tbl_hotel] ([hotel_id], [hotel_name], [address], [countryid], [action]) VALUES (13, N'1123', N'123124', 1, 0)
INSERT [dbo].[tbl_hotel] ([hotel_id], [hotel_name], [address], [countryid], [action]) VALUES (14, N'trihungdeptrai', N'1', 1, 1)
INSERT [dbo].[tbl_hotel] ([hotel_id], [hotel_name], [address], [countryid], [action]) VALUES (15, N'Diamond1', N'123', 1, 1)
INSERT [dbo].[tbl_hotel] ([hotel_id], [hotel_name], [address], [countryid], [action]) VALUES (16, N'trihungdeptraiso1', N'vinh', 1, 1)
SET IDENTITY_INSERT [dbo].[tbl_hotel] OFF
SET IDENTITY_INSERT [dbo].[tbl_room] ON 

INSERT [dbo].[tbl_room] ([room_id], [room_name], [floor], [price], [hotel_id], [category_id], [isAction]) VALUES (1, N'A01', N'1', 12, 3, 1, 0)
INSERT [dbo].[tbl_room] ([room_id], [room_name], [floor], [price], [hotel_id], [category_id], [isAction]) VALUES (2, N'A02', N'2', 14, 3, 1, 1)
INSERT [dbo].[tbl_room] ([room_id], [room_name], [floor], [price], [hotel_id], [category_id], [isAction]) VALUES (3, N'A03', N'3', 100, 3, 2, 1)
INSERT [dbo].[tbl_room] ([room_id], [room_name], [floor], [price], [hotel_id], [category_id], [isAction]) VALUES (5, N'AF_12', N'10', 122, 2, 1, 1)
INSERT [dbo].[tbl_room] ([room_id], [room_name], [floor], [price], [hotel_id], [category_id], [isAction]) VALUES (6, N'A04', N'4', 155, 3, 3, 1)
INSERT [dbo].[tbl_room] ([room_id], [room_name], [floor], [price], [hotel_id], [category_id], [isAction]) VALUES (7, N'A05', N'5', 111, 3, 2, 1)
INSERT [dbo].[tbl_room] ([room_id], [room_name], [floor], [price], [hotel_id], [category_id], [isAction]) VALUES (8, N'A06', N'1', 123, 3, 3, 1)
INSERT [dbo].[tbl_room] ([room_id], [room_name], [floor], [price], [hotel_id], [category_id], [isAction]) VALUES (9, N'A07', N'2', 11, 3, 1, 1)
INSERT [dbo].[tbl_room] ([room_id], [room_name], [floor], [price], [hotel_id], [category_id], [isAction]) VALUES (10, N'A08', N'1', 55, 3, 1, 1)
INSERT [dbo].[tbl_room] ([room_id], [room_name], [floor], [price], [hotel_id], [category_id], [isAction]) VALUES (11, N'B01', N'3', 13, 3, 1, 1)
INSERT [dbo].[tbl_room] ([room_id], [room_name], [floor], [price], [hotel_id], [category_id], [isAction]) VALUES (12, N'B02', N'1', 13, 1, 1, 1)
INSERT [dbo].[tbl_room] ([room_id], [room_name], [floor], [price], [hotel_id], [category_id], [isAction]) VALUES (13, N'A07', N'2', 12, 1, 1, 1)
INSERT [dbo].[tbl_room] ([room_id], [room_name], [floor], [price], [hotel_id], [category_id], [isAction]) VALUES (18, N'AK47', N'2', 14, 1, 1, 1)
INSERT [dbo].[tbl_room] ([room_id], [room_name], [floor], [price], [hotel_id], [category_id], [isAction]) VALUES (19, N'1', N'1', 2, 1, 1, 1)
INSERT [dbo].[tbl_room] ([room_id], [room_name], [floor], [price], [hotel_id], [category_id], [isAction]) VALUES (20, N'AK48', N'1', 12, 1, 1, 1)
INSERT [dbo].[tbl_room] ([room_id], [room_name], [floor], [price], [hotel_id], [category_id], [isAction]) VALUES (21, N'AK47', N'2', 13, 1, 1, 1)
INSERT [dbo].[tbl_room] ([room_id], [room_name], [floor], [price], [hotel_id], [category_id], [isAction]) VALUES (22, N'A01A', N'1', 12, 1, 1, 1)
INSERT [dbo].[tbl_room] ([room_id], [room_name], [floor], [price], [hotel_id], [category_id], [isAction]) VALUES (23, N'SuperVip', N'1', 12, 1, 1, 1)
INSERT [dbo].[tbl_room] ([room_id], [room_name], [floor], [price], [hotel_id], [category_id], [isAction]) VALUES (24, N'SuperVip1a1', N'1', 13, 1, 1, 0)
SET IDENTITY_INSERT [dbo].[tbl_room] OFF
SET IDENTITY_INSERT [dbo].[tbl_user] ON 

INSERT [dbo].[tbl_user] ([userid], [username], [password], [fullname], [role], [action]) VALUES (1, N'admin', N'1', N'nguyentrihung', N'admin', 1)
INSERT [dbo].[tbl_user] ([userid], [username], [password], [fullname], [role], [action]) VALUES (2, N'tutai', N'1', N'nguyen tu tai', N'user', 0)
INSERT [dbo].[tbl_user] ([userid], [username], [password], [fullname], [role], [action]) VALUES (3, N'trihung', N'1', N'deptrai', N'customer', 1)
INSERT [dbo].[tbl_user] ([userid], [username], [password], [fullname], [role], [action]) VALUES (4, N'duyanh', N'1', N'nguyen duy anh', N'customer', 1)
INSERT [dbo].[tbl_user] ([userid], [username], [password], [fullname], [role], [action]) VALUES (5, N'hoadnt', N'1', N'nguyen thanh hoa', N'user', 0)
INSERT [dbo].[tbl_user] ([userid], [username], [password], [fullname], [role], [action]) VALUES (11, N'tuanmt', N'1', N'tuandeptrai', N'user', 0)
INSERT [dbo].[tbl_user] ([userid], [username], [password], [fullname], [role], [action]) VALUES (12, N'nam', N'1', N'namxautrai', N'user', 0)
INSERT [dbo].[tbl_user] ([userid], [username], [password], [fullname], [role], [action]) VALUES (19, N'tuananhdeptrai', N'1', N'nguyentrihung', N'customer', 1)
INSERT [dbo].[tbl_user] ([userid], [username], [password], [fullname], [role], [action]) VALUES (20, N'tuananhdeptrai1', N'1', N'nguyentrihung', N'customer', 1)
INSERT [dbo].[tbl_user] ([userid], [username], [password], [fullname], [role], [action]) VALUES (21, N'tuananhdeptrai1a1', N'1', N'nguyentrihung', N'customer', 1)
SET IDENTITY_INSERT [dbo].[tbl_user] OFF
USE [master]
GO
ALTER DATABASE [BookingHotel] SET  READ_WRITE 
GO
